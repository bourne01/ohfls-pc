<template>
<div class="student-content">       
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
				<el-button type="text" size="small" class="edit" @click="openSubCourse=true" >编辑</el-button>    
				<el-button type="text" 
				size="mini"
				@click.native.prevent="deleteRow(scope.$index,studentList)"
				>                                                        <!--删除图标的样式，默认是不可编辑的，当选择器被选中时可被编辑 -->
				<img :src="require('../../../assets/delete-oc.png')" alt=""  >
				</el-button>
				</template>      
		</el-table-column>
	</el-table>
	<div class="footer">
		<el-checkbox @change="toggleSelection(studentList)" class="select-all">全选</el-checkbox>
		<div class="amount">(当前页共条)</div>
		<el-button class="footer-button" style="margin-left:2.2%;color:#ff7a7b">删除</el-button>
		<el-button class="footer-button" style="margin-left:1.2%;color:#707079">关闭选课</el-button>
		<el-button class="footer-button" style="margin-left:1.2%;color:#14b25a">开启选课</el-button>
		<el-pagination
			background
			layout="prev, pager, next"
			:total="140">
			:page-size="8"
		</el-pagination>    <!--翻页的-->
	</div>
</div>
</template>

<script>
import{mapActions,mapState} from 'vuex'
import TaskState from './elective-state'
export default {
	components:{
		TaskState
	},
data() {
	return {
	
	}
},
methods:{
	toggleSelection(rows) {   /* 全选复选框*/
	if (rows) {
		rows.forEach(row => {
		this.$refs.multipleTable.toggleRowSelection(row);
		});
	} else {
		this.$refs.multipleTable.clearSelection();
	}
	},
	handleSelectionChange(val) {
	this.multipleSelection = val;
	},
		deleteRow(index, rows) {            //删除按钮的弹框，并进行进一步的确认或者取消
	this.$confirm('确认删除该课程吗?', '提示', {
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		type: 'warning'
	}).then(() => { 
		rows.splice(index, 1);
		this.$message({
		type: 'success',
		message: '删除成功!'
		});
	}).catch(() => {
		this.$message({
		type: 'info',
		message: '已取消删除'
		});          
	});
	},
	toggleSelection(rows) {
	if (rows) {
		rows.forEach(row => {
		this.$refs.multipleTable.toggleRowSelection(row);
		});
	} else {
		this.$refs.multipleTable.clearSelection();
	}
	},
	handleSelectionChange(val) {
	this.multipleSelection = val;
	},
	filterGender(value, row) {       //筛选性别
	return row.gender === value;
	},
	filterStatus(value, row) {       //筛选选课状态
	return row.electiveStatus === value;
	},
	...mapActions('election',['getTaskList'])
},
computed:{
	...mapState('election',{
	taskList:state => state.taskList
	})
},
mounted(){
	/**获取选课任务列表 */
	let url ='xkTask!query2.action';
	let params = {xkpId:44};
	this.getTaskList({url,params})
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



