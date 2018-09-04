<template>
<div class="student-status" style="background-color:#E9EEF3;">
	<el-table
	ref="multipleTable"
	:data="stuCouRelations"
	tooltip-effect="dark"
	style="width: 100%"
	@selection-change="handleSelectionChange"
	border
	>
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
		label="性别"
		:filters="[ { text: '男', value: '男' },
				{text:'女',value:'女'},]"
		:filter-method="filterGender"
		filter-placement="bottom-end">  
		</el-table-column>
		<el-table-column
		prop="couName"
		label="课程名称">         <!--课程名称列-->
		</el-table-column>
		<el-table-column
		prop="couNO"
		label="课程编号">     <!--课程编号列-->
		</el-table-column>
		<el-table-column			
			label="可选班级"
			show-overflow-tooltip>
			<template slot-scope="scope">
				{{scope.row.claNms|handleNull}}
			</template>
		</el-table-column>
		<el-table-column	
		label="选课状态"
		:filters="[ { text: '已选', value: '已选' },
				{text:'不可选',value:'不可选'},]"
		:filter-method="filterStatus"
		filter-placement="bottom-end">  
			<template slot-scope="scope">
				{{scope.row.canSel===0?'不可选':'可选'}}
			</template>
		</el-table-column>
		<el-table-column
			label="学生状态">
			<template slot-scope="scope">
				{{scope.row.stuOpState|stateConverter('Student')}}
			</template>
		</el-table-column>
		<el-table-column
			label="任务状态">
			<template slot-scope="scope">
				{{scope.row.taskState|stateConverter('Task')}}
			</template>
		</el-table-column>			
		<el-table-column
		label="操作">
			<template slot-scope="scope"> 
				<el-button type="text" size="medium" @click="onClickDetail(scope.row)">查看选课详情</el-button>
			</template>      
		</el-table-column>
		</el-table>
		<div class="footer">
			<el-checkbox @change="toggleSelection(courseTable)" class="select-all">全选</el-checkbox>
			<div class="amount">(当前页共条)</div>
			<el-pagination
				background
				layout="prev, pager, next"
				:total="140">
				:page-size="8"
			</el-pagination>    <!--翻页的-->
		</div>
		<elective-detail 
			:is-pop="isPop" 
			:elective-detail="electDetail"
			@dialog-close="onDialogClose"></elective-detail>
	</div>
</template>

<script>
import {mapActions} from 'vuex'
import ElectiveDetail from './elective-detail'
export default {
	components:{
		ElectiveDetail
	},
	data() {
		return {
			isPop:false,
			stuCouRelations:[],
			multipleSelection: [],
			electDetail:[],
			}
	},
	methods: {
		/**
		 * @function 点击查看选课详情
		 * @param {当前表格行数据} curRow
		 */
		onClickDetail(curRow){
			this.electDetail.push(curRow);
			this.isPop = true;
		},
		/**
		 * @function 监听对话框子组件关闭事件，然后执行初始化 
		 */
		onDialogClose(){
			this.isPop = false;//关闭子组件
			this.electDetail = [];//初始化对话框内容为空
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
		filterGender(value, row) {       //筛选性别`
		return row.gender === value;
		},
		filterStatus(value, row) {       //筛选选课状态
		return row.electiveStatus === value;
		},
		...mapActions('election',['getStuCouRelations'])
	},
	filters:{
		/**
		*@function 根据状态值转化为状态名
		*@param {输入值} val
		*@param {状态类型} type
		*@return 返回状态名称
		*/
		stateConverter(val,type){
			let stuStates = ['不可见','可选课'];
			let taskStates = ['未开启','开启','关闭','完成'];
			let value = val;
			switch(type){
				case 'Student':
					value = stuStates[val-1];
					break;
				case 'Task':
					value = taskStates[val-1];
					break;
				default:
					value = '未知'
			}
			return value;
		},
		/**
		*@function 处理输入值为空
		*/
		handleNull(val){
			if(!val){
				return '未知'
			}
			return val;
		}
	},
	mounted(){		
		this.getStuCouRelations({})
			.then(res => {
				console.log(res);
				if(res.success){
					this.stuCouRelations = res.dataList;
				}
			})
			.catch(err => {
				console.log(err)
			})
		}
}
</script>

<style scoped>
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
	.student-status .el-pager li:not(.disabled).active {  /*翻页的背景色*/
	background-color:rgb(13,113,74);
	
  	}
   .student-status .el-table__column-filter-trigger i{
		margin-left: 3px;
		font-size: 16px;
		}
</style>



