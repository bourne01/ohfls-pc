<template>
    <div class="comptele-course">
        <div>{{couStateName}}</div>
        <button  
			@click="onStateBtnClick(courseState,courseAudit)" 
			:class="{verify:courseState===1,
					confirm:courseState===2,
					end:courseState===3,
					evaluate:courseState===4,
					finish:courseState===5}" 
			class="btn" >
			{{couActionName}}</button>
			<button
				v-if="courseState===5"
				class="btn2 end"
				@click="onStateBtnClick(courseState,courseAudit)" 
			>确认
			</button>
     <el-dialog
		title="评价管理"
		:visible.sync="isEvaluationShow"
		v-model="activeName"
		width="45%"      
		>     
        <el-tabs v-model="activeName" @tab-click="handleClick"  >
			<el-tab-pane label="管理员评价教师" name="first"><manager-evaluate-teacher></manager-evaluate-teacher></el-tab-pane>
        <el-tab-pane label="教师评价学生" name="second"><teacher-evaluate-student></teacher-evaluate-student></el-tab-pane>
        <el-tab-pane label="学生评价课程" name="third"></el-tab-pane>
        </el-tabs>
      
      </el-dialog>
    </div>
  
</template>



<script>
import ManagerEvaluateTeacher from '../course-evaluate/view-evaluation/manager-evaluate-teacher.vue'
import TeacherEvaluateStudent from '../course-evaluate/view-evaluation/teacher-evaluate-student.vue'
import { mapState, mapActions } from 'vuex';
import { getEvaluations,getElectResults,
		changeStuCouState,changeCouState,changeEvalState} from '../../../api/election.js'
export default {
    props:['current-course'],
    components:{
      ManagerEvaluateTeacher,
      TeacherEvaluateStudent
      
    },
  data() {
      	return {
			isEvaluationShow: false,//评价管理弹窗是否开启
			activeName:'first',
			couStateName:'',//课程状态名称
			couActionName:'',//课程状态处理名称
			couStateVal:0,//课程状态值
			evaluateState:'',//师生互评状态（互评状态 2普通(默认)、3确认）
			studentCourseIds:[],
			courseState:'',//课程状态值
			courseAudit:'',//课程审计状态值
      	};
	},
	computed:{	
		...mapState('election',{

		})
	},
    methods: {
        handleClick(tab, event) {
			console.log(tab, event);
		},
		/**
		 * @function 点击状态按钮，改变状态
		 * @param {课程状态} courseState
		 * @param {课程审计状态} courseAudit
		 */
		onStateBtnClick(courseState,courseAudit){
			if(courseAudit === 1){//课程未审核
				let Audited = 2;//课程已审核
				//设置设计状态为已审核
				this.setCourseState(this.currentCourse.selCouId,undefined,Audited);
			}else{
				if(courseState === 5 && this.evaluateState === 3){//课程已经完成且师生互评完成
					this.isEvaluationShow = true;//打开评价管理弹窗
				}else if(courseState === 2){//开启选课(默认)
					//确认选课结果
					let _courseState = 4;//选课结果被确认
					this.setCourseState(this.currentCourse.selCouId,_courseState,undefined);
				}else if(courseState === 4){//选课结果已被确认
					//设置课程状态为完成，且同时开启师生互评状态
					let _courseState = 5;//选课结果被确认
					this.setCourseState(this.currentCourse.selCouId,_courseState,undefined);
					this.getStudentCourseIds(this.currentCourse.selCouId);
				}else if(courseState === 5){//授课已经完成,且教师可评价学生
					//确认师生互评已经完成
					this.setEvaluationDone(this.studentCourseIds);
				}
			}
		},

		/**@function 根据状态值，显示不同的状态值 
		 * @param {师生互评状态} evalState 
		 */
		showStateName(evalState){
			if(this.currentCourse.audit == 1){//课程未审核
				this.couActionName = '审核';
				this.couStateName = '课程状态未审核';
				this.couState = 1;//审核
				
			}else{
				if(this.currentCourse.couState == 2){//开启选课(默认)
					this.couActionName = '确认选课';
					this.couStateName = "已审核，学生选课中···";
					this.couState = 2;//确认选课
				}else if(this.currentCourse.couState == 4){//选课结果已被确认
					this.couActionName = '结束上课';
					this.couStateName = '课程正在进行中···';
					this.couState = 5;//结束上课
				}else if(this.currentCourse.couState == 5){//授课已经完成,且教师可评价学生
					this.couStateName = '正在评价中...';
					this.couActionName = '评价管理';
					this.couState = 4;//确认评价，即关闭评价 
					//关闭师生互评，evalState  ; //2普通(默认)、3确认(关闭评价)
				}else if(this.currentCourse.couState == 5 && evalState == 3){//授课已经完成
					this.couStateName = '课程已完成，已记录学分';
					this.couActionName = '查看评价';
					//
				}
			}
		},
		/**
		 * @function 根据课程Id,设置课程状态
		 * @param {课程Id} courseId 
		 * @param {课程开课状态 2开启(可选)(默认) 3关闭(不可选) 4确认 5完成} courseState
		 * @param {课程审核状态 1未审核(默认) 2已审核} auditState
		 */
		setCourseState(courseId,courseState,auditState){
			let params = {
				selCouIds:courseId,
				couState:courseState,
				audit:auditState};
			changeCouState(params)
				.then(res => {
					if(res.data.success){
						this.$message({
							type:'success',
							message:res.data.message
						})
						/**
						 * 根据状态值，转化为相应的状态名称
						 */
						console.log(courseState);
						switch(courseState){
							case 4:
								this.couActionName = '结束上课';
								this.couStateName = '课程正在进行中···';
								this.courseState = 4;//课程上课结束
								break;
							/* case 5:
								this.couStateName = '正在评价中...';
								this.couActionName = '评价管理';
								this.courseState = 5;//课程已经完成
								break; */
							default:
						}
						if(auditState === 2){
							this.couActionName = '确认选课';
							this.couStateName = "已审核，学生选课中···";
						}
					}else{
						this.$message({
							type:'error',
							message:res.data.message
						})
					}
				})
		},

		/**
		 * @function 根据被选课Id，获取学生选课Id
		 * @param {学生所学课程的Id} selCouId
		 */
		getStudentCourseIds(selCouId){
			let url = "stuCou!query.action";
			let params = {selCouId};
			return getElectResults(url,params)
				.then(res => {
					let objData = res.data;
					let studentCourseIds = [];//学生选课Id
					if(objData.success){
						for(let item of objData.dataList){
							studentCourseIds.push(item.stuCouId);
						}
					}else{
						console.log(objData.message);
					}
					//this.studentCourseIds = studentCourseIds;
					if(studentCourseIds.length != 0){
						this.setEvaluatable(studentCourseIds.toString(),3,2,2);
					}
				})
		},

		/**
		 * @function 根据学生选课Id,更改教师评价与学生评价状态为可评价，即开启师生评价,
		 * 或确认选课状态
		 * @param {学生选课Id} studentCourseIds
		 * @param {选课状态 1预选 2选中(默认) 3确认} electState
		 * @param {教师评价状态控制 1不可评(默认) 2可评价} teacherEvaluateState
		 * @param {学生评价状态控制 1不可评(默认) 2可评价} studentEvaluateState
		 */
		setEvaluatable(studentCourseIds,electState,teacherEvaluateState,studentEvaluateState){
			let params = {
						stuCouIds:studentCourseIds,
						state:electState,
						evalStateThr:teacherEvaluateState,
						evalStateStu:studentEvaluateState};
			return  changeStuCouState(params)
				.then(res => {
					if(res.data.success){
							this.$message({
							type:'success',
							message:res.data.message
						})
						this.couStateName = '正在评价中...';
						this.couActionName = '评价管理';
						this.courseState = 5;//课程已经完成
					}else{
						this.$message({
							type:'error',
							message:res.data.message
						})
					}
					
				})
		},
		
		/**@function 更改状态，确认师生互评已经完成 
		 * @param {学生选课Id} studentCourseIds
		 * @param {师生互评状态 2普通(默认)、3确认}
		*/
		setEvaluationDone(studentCourseIds){
			let params = {
					stuCouIds:studentCourseIds,
					evalState:3}
			changeEvalState(params)
		},

		/**
		 * @function 获取师生互评状态
		 * @param {学生所学课程的Id} selCouId
		 */
		getInterEvaluateState(selCouId){
			let url = 'stuCouEval!query2.action';
			let params = {selCouId};
			return getEvaluations(url,params)
				.then(res => {
					let objData = res.data;
					if(objData.success){
						this.evaluateState = objData.evalState; 
					}else{
						console.log(objData.message);
					}
				})
		},
		/**
		 * @function 异步转同步
		 * @param {当前课程Id} currentCourseid
		 */
		toSync:async function(currentCourseid){
			await this.getInterEvaluateState(currentCourseid);
			await this.showStateName(this.evaluateState);
		}
	},
	created(){
		this.courseState = this.currentCourse.couState;
		this.courseAudit = this.currentCourse.audit;
	},
    mounted(){
		//this.showStateName();
		this.toSync(this.currentCourse.selCouId);
	}
  };
</script>

<style scoped>
  .btn{     /*按钮的整体样式*/
    border: none;
    height: 28px;
    width:70px;
    cursor: pointer;
    border-radius: 2px;
    line-height: 28px;  
    color: white;
    margin-top: 5px;
  }
  .btn2{     /*按钮的整体样式*/
    border: none;
    height: 28px;
    width:50px;
    cursor: pointer;
    border-radius: 2px;
    line-height: 28px;  
    margin-top: 5px;
	color:#fff;
  }
  .verify{           /*未审核状态的样式*/
    border: 1px solid #448af5;
    background-color: #5b9bfd; 
  }
  .confirm{       /*已审核状态的样式*/
      border: 1px solid #14b25a;
      background-color: #1cd06c;
      
  }
  .end{           /*正在上课状态的样式*/
    border: 1px solid #d45447;
    background-color: #d45447;
  }
  .evaluate{        /*正在评价状态的样式*/
    border:1px solid #14b25a;
    background-color: #1cd06c;
  }
  .finish{          /*完成状态的样式*/
    border:1px solid #b5b5c0;
    background-color:white; 
    color:black;
  
  }
</style>

<style>
 
 .comptele-course .el-tabs__item  {
  color:#707079;
  margin-bottom:5px;
 
 }
 .comptele-course .el-tabs__item.is-active{
   color:#0d714a;
 }
 .comptele-course .el-dialog  {    /*设置弹框整体的样式*/
   height:700px;
  
 }                                                               
 .comptele-course .el-dialog__header{             /*设置弹框的头部*/
   background-color :#0d714a;
   height: 50px;
   color:#707079; 
   margin:0px;
 }
  .comptele-course  .el-dialog__header .el-dialog__title{       /*设置头部的标题样式*/ 
    color:white;
    float: left;
    line-height:10px;
    margin-left:20px;
    font-size:16px;
    font-family:'MicrosoftYaHei';
 }
 .comptele-course .el-dialog__headerbtn .el-dialog__close{      /*设置头部关闭的样式*/
   color:white;
   font-size:24px;
   margin-top:-30px;
 }
 .comptele-course .el-tabs__active-bar.is-top{                /*设置tabs的滑动条样式*/
    background-color :green;
    
    
 }
 .comptele-course .el-dialog__body {                        /*设置弹框body内的内填充为0*/
    padding:0;  
    width:100%;
 
  
 }
 
 .comptele-course  .el-tabs__nav{
   margin:0 0 0 30%;
   width:200px;
 }
 .comptele-course .el-tabs__header{       /*设置tabs的下边距为0*/
    margin:0;
 }
 .comptele-course  .el-tabs__nav-wrap.is-top{
    width:100%;
 }
 
</style>








