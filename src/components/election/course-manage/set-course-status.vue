<template>
    <div class="comptele-course">
        <div>{{couStateName}}</div>
        <button  
			@click="elvaluateManage = true" 
			:class="{verify:couState===1,
					confirm:couState===2,
					end:couState===3,
					evaluate:couState===4,
					finish:couState===5}" 
			class="btn" >
          {{couActionName}}</button>
		  <button
		  	v-if="couState===4"
		  	class="btn2 end"
		  >确认
		  </button>
     <el-dialog
      title="评价管理"
      :visible.sync="elvaluateManage"
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
  export default {
    props:['state'],
    components:{
      ManagerEvaluateTeacher,
      TeacherEvaluateStudent
      
    },
  data() {
      return {
        elvaluateManage: false,
        activeName:'first',
		couStateName:'',//课程状态名称
		couActionName:'',//课程状态处理名称
		couStateVal:0,//课程状态值
		couState:''

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
		/**@function 根据状态值，显示不同的状态值 */
		showStateName(){
			if(this.state.audit == 1){//课程未审核
				this.couActionName = '审核';
				this.couStateName = '课程状态未审核';
				this.couState = 1;//审核
			}else{
				if(this.state.couState == 2){//开启选课(默认)
					this.couActionName = '确认选课';
					this.couStateName = "已审核，学生选课中···";
					this.couState = 2;//确认选课
				}else if(this.state.couState == 4){//选课结果已被确认
					this.couActionName = '结束上课';
					this.couStateName = '课程正在进行中···';
					this.couState = 3;//结束上课
				}else if(this.state.couState == 3){//授课已经完成,且教师可评价学生
					this.couStateName = '正在评价中...';
					this.couActionName = '评价管理';
					this.couState = 4;//确认评价，即关闭评价 
				}else if(this.state.couState == 5 && this.state.thrOpState == 2){//授课已经完成
					this.couStateName = '课程已完成，已记录学分';
					this.couActionName = '查看评价';
					this.couState = 5;//确认评价，即关闭评价 
				}
			}
		},
    },
    mounted(){
		this.showStateName();
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








