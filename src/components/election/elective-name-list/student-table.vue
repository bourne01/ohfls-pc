<template>
<div class="student-content"
	v-loading="isLoading"
	element-loading-text="拼命设置中"
	background="rgba(0, 0, 0, 0.8)">       
	<el-table
		:data="taskList"
		border
		ref="multipleTable"
		style="width: 100%"
		tooltip-effect="dark"
		@selection-change="handleSelectionChange">    <!--学生列表-->
		<el-table-column
		type="selection"
		width="55">
		</el-table-column>
		<el-table-column
		prop="stuName"
		label="姓名"
		width="180">
		</el-table-column>
		<el-table-column
		prop="stuNO"
		label="学号"
		width="180">
		</el-table-column>
		<el-table-column
		prop="stuSex"
		width="100"
		label="性别"
		:filters="[ { text: '男', value: '男' },
				{text:'女',value:'女'},]"
		:filter-method="filterGender"
		filter-placement="bottom-end">  
		</el-table-column>
		<el-table-column
			prop="startTime"
			label="选课开始时间">         <!--开始选课时间-->
		</el-table-column>
		<el-table-column
			prop="endTime"
			label="选课结束时间">
		</el-table-column>
		<el-table-column
			prop="overTime"
			label="选课完成时间">
		</el-table-column>
		<el-table-column
			prop="electiveStatus"
			label="选课状态"
			:filters="[ { text: '开启', value: '开启' },
					{text:'关闭',value:'关闭'},]"
			:filter-method="filterStatus"
			filter-placement="bottom-end"
			width="110">  
		>
			<template slot-scope="scope">
				<task-state :task-state="scope.row.taskState"></task-state>
			</template>
		</el-table-column>
		<el-table-column
			label="操作">
			<template slot-scope="scope"> 
				<!-- <el-button type="text" size="small" class="edit" @click="openSubCourse=true" >编辑</el-button>     -->
				<edit-task :elective-task="scope.row"></edit-task>
				<el-button type="text" 
					size="small"
					icon="el-icon-delete"
					@click.native.prevent="deleteTask(scope.row)"
					>                                                        <!--删除图标的样式，默认是不可编辑的，当选择器被选中时可被编辑 -->
					<!-- <img :src="require('../../../assets/delete-oc.png')" alt=""  > -->
				</el-button>
				</template>      
		</el-table-column>
	</el-table>
	<div class="footer">
		<el-checkbox @change="toggleSelection(taskList)" class="select-all">全选</el-checkbox>
		<div class="amount">(当前页共条)</div>
		<el-button 
			class="footer-button" style="margin-left:2.2%;color:#ff7a7b"
			@click="deleteTask()">批量删除</el-button>
		<el-button class="footer-button" style="margin-left:1.2%;color:#707079">关闭选课</el-button>
		<el-button class="footer-button" style="margin-left:1.2%;color:#14b25a">开启选课</el-button>
		<el-pagination
			background
			layout="prev, pager, next"
			:total="total"
			:page-size='pageSize'
			@current-change="onCurrentChange">
		</el-pagination>    <!--翻页的-->
	</div>
</div>
</template>

<script>
import{mapActions,mapState} from 'vuex'
import EditTask from './edit-task-pop'
import TaskState from './elective-state'
import { xhrErrHandler } from '../../../utils/util';
import { delElectDuties, editElectDuty } from '../../../api/election';
export default {
	components:{
		TaskState,
		EditTask
	},
	data() {
		return {
			total:1,//默认一页
			pageSize:50,//每页放50个任务
			studentIds:[],//被用户选中的学生选课Id
			allStuIds:[],//来自接口请求的学生选课记录Id
			isLoading:false,
		}
	},
	methods:{
		toggleSelection(rows) {   /* 全选复选框*/
			if (rows) {
				rows.forEach(row => {
				this.$refs.multipleTable.toggleRowSelection(row);
				});
			} else {
				/* this.$refs.multipleTable.clearSelection(); */
			}
		},
		handleSelectionChange(val) {
			let stuIds = [];
			for(let item of val)
				stuIds.push(item.stuId);
			this.studentIds = stuIds;
		},
		/**
		 * @function 删除学生选课任务，可以是多个学生
		 */
		deleteTask(studentTask) {            //删除按钮的弹框，并进行进一步的确认或者取消
			this.$confirm('确认删除该生选课任务吗?', '提示', {
				confirmButtonText: '确定',
				cancelButtonText: '取消',
				type: 'warning'
			}).then(async () => {
				let params = {};
				if(typeof studentTask === 'object'){//来自表格操作列中删除按钮，即删除一条记录
					params = {
						xkpId:studentTask.xkpId,
						stuIds:studentTask.stuId,
					}
					await editElectDuty({
						xkpId:studentTask.xkpId,
						stuIds:studentTask.stuId,
						TaskState:1
					})
				}else if(this.studentIds.length === 0){//来自底部的批量删除按钮
					this.isLoading = true;									
					await this.getTasks(0,100000);
					await editElectDuty({
						xkpId:this.currentPlanId,
						stuIds:this.allStuIds.toString(),
						TaskState:1
					})	
					params = {
						xkpId:this.currentPlanId,
						stuIds:this.allStuIds.toString()
					}						
				}else{
					params = {
						xkpId:this.currentPlanId,
						stuIds:this.studentIds.toString()
					}
					await editElectDuty({
						xkpId:this.currentPlanId,
						stuIds:this.studentIds.toString(),
						TaskState:1
					})
				}
				delElectDuties(params)
					.then(res => {
						if(res.data.success){
							this.getTasks();
							this.$message({
							type: 'success',
							message: '删除成功!'
						});
						}else{
							this.$message.error(res.data.message)
						}
						this.isLoading = false;
					})
					.catch(err => {
						xhrErrHandler(err,this.$router,this.$message)
						this.isLoading = false;
					})
			}).catch(() => {
				this.$message({
				type: 'info',
				message: '已取消删除'
				});          
			});
		},
		filterGender(value, row) {       //筛选性别
			return row.gender === value;
		},
		filterStatus(value, row) {       //筛选选课状态
			return row.electiveStatus === value;
		},
		...mapActions('election',['getTaskList']),
		/**
		 * @function 获取选课任务列表
		 */
		getTasks(start=0,limit=50){
			let url ='xkTask!query2.action';
			let params = {
				xkpId:this.currentPlanId,
				limit,
				start
			};
			return this.getTaskList({url,params})
				.then(res => {
					this.total = res.total;
					this.allStuIds = [];
					for(let item of res.dataList)
						this.allStuIds.push(item.stuId);
				})
				.catch(err => {
					xhrErrHandler(err,this.$router,this.$message)
				})
		},
		/**
		 * @function 监听点击改变页码事件
		 * @param {当前页码} val
		 */
		onCurrentChange(val){
			this.getTasks((val-1)*50);
		}
	},
	computed:{
		...mapState('election',{
			taskList:state => state.taskList,
			currentPlanId:state => state.currentPlanId,
		})
	},
	mounted(){
		this.$root.bus.$on('update-task-list',() => {
			this.getTasks();
			console.log('fjalfaljfalj');
		});		
		/**获取选课任务列表 */
		this.getTasks();
	},
	destroyed(){
		this.$root.bus.$off('update-task-list');
	}
}
</script>
<style scoped>
.edit{
	float: left;
	margin: 3px  -10%  0 30%;
}
.footer{          /*底部样式*/
	width: 100%;
	height: 50px;
	box-sizing: border-box;
	line-height: 50px;

}
.select-all{      /*全选样式*/
	float: left;
	margin-left: 1.5%
}
.amount{          /*计数样式*/
	float: left;
	font-family: 'Simsun';
	font-size: 12px;
	color: #707079;

}
.footer-button{   /*底部按钮的全局样式*/
	float: left;
	margin-top: 12px;
	width: 88px;
	height: 28px;
	line-height: 32%;
	font-size: 12px;

}
.el-pagination{   /*翻页按钮样式*/
	float: right;
	margin-top: 8px;
}
</style>

<style>
.student-content .footer .el-pagination.is-background .el-pager li:not(.disabled).active {  /*翻页的背景色*/
	background-color:rgb(13,113,74);
}   
.student-content th{       /*  表头字体居中 */
	text-align: center;
	background-color:#f6f9fb;
	color: #2b2b32;
	}
 .student-content .el-table__column-filter-trigger i{
		margin-left: 3px;
		font-size: 16px;
  	}
</style>



