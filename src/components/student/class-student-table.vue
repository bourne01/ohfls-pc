<template>
	<div class="class-student-table">    <!--这是右边新增学生表格-->
		<el-table
			ref="multipleTable"
			:data="classTable"
			tooltip-effect="dark"
			border
			max-height="625"
			@selection-change="handleSelectionChange">
			<el-table-column
				type="selection"
				min-width="55">
			</el-table-column>
			<el-table-column
				prop="stuNO"
				label="学号"
				min-width="120"
				>
			</el-table-column>
			<el-table-column
				prop="stuName"
				label="姓名">
			</el-table-column>
			<el-table-column
				prop="stuSex"
				label="性别"				>
			</el-table-column>
			<el-table-column
				prop="credit"
				label="学分">
			</el-table-column>
			<el-table-column				
				label="去向"
				:filters="studentStates"
				:filter-method="filterTag1"
				filter-placement="bottom-end">
				<template slot-scope="scope">
					<span :class="{
							normal:scope.row.stuTo==2,
							abnormal:scope.row.stuTo==3,
							}">
						{{scope.row.stuTo|convertValueToName('Student')}}
					</span>					
				</template>
			</el-table-column>
			<el-table-column
				label="状态"
				:filters="studentRecordStates"
				:filter-method="filterTag2"
				filter-placement="bottom-end">	
				<template slot-scope="scope">
					<span :class="{
							normal:scope.row.stuState==2,
							abnormal:scope.row.stuState==3,
							}">
						{{scope.row.stuState|convertValueToName('Record')}}
					</span>	
				</template>
			</el-table-column>    
			<el-table-column    
				prop="Jump"
				label="跳转"
				min-width="115"
				fixed="right">     <!--这是功能按钮--> 
			<template slot-scope="scope">
				<el-button type="text" >跳转至学生端</el-button>
			</template>
			</el-table-column>
			<el-table-column
				min-width="160"    
				prop="operation"
				label="操作"
				fixed="right">     <!--这是功能按钮--> 
				<template slot-scope="scope">
					<el-button type="text" >编辑</el-button>
					<el-button 
						type="text" size="small" 
						@click="dialogVisible2 = true"
						class="reset">重设密码</el-button>
					<el-button type="text" icon="el-icon-delete"></el-button>
				</template>
			</el-table-column>
		</el-table>
		<div class="postion1"> <!--这是按钮位置-->
			<el-button @click="toggleSelection(tableData)">全选</el-button>
			<el-button @click="toggleSelection()">批量删除</el-button>
		</div> 
		<div class="block"><!--这是翻页功能-->
			<el-pagination
			@size-change="handleSizeChange"
			@current-change="handleCurrentChange"
			:current-page.sync="currentPage1"
			:page-size="10"
			layout="total, prev, pager, next"
			:total="100">
			</el-pagination>
		</div>
	</div>
</template>
<style scoped>

</style>
<script>
import {mapActions,mapState} from 'vuex'
export default {
	data() {
		return {
			dialogVisible2: false, /*重设密码dialog事件*/
			multipleSelection: [],
			currentPage1:1,
			studentStates:[//学生状态
				{ text: '休学', value: 3 }, 
				{ text: '退学', value: 4 }, 
				{ text: '毕业', value: 5 },
				{ text: '正常', value: 2 }],
			studentRecordStates:[//学生记录状态
				{ text: '关闭', value: 3 }, 
				{ text: '历史', value: 4 }, 
				{ text: '正常', value: 2 }],
			
		}
	},
	methods: {		
		toggleSelection(rows) {
			if (rows) {
			rows.forEach(row => {
				this.$refs.multipleTable.toggleRowSelection(row);
			});
			} else {
			this.$refs.multipleTable.clearSelection();
			}
		},
		filterTag1(value, row) {     /* 去向筛选方法*/
			return row.where === value;
		},
			filterTag2(value, row) {   /* 状态筛选方法*/
			return row.state === value;
		},
		handleSelectionChange(val) {   
			this.multipleSelection = val;
		},
		handleSizeChange(val) {
			console.log(`每页 ${val} 条`);
		},
		handleCurrentChange(val) {
			console.log(`当前页: ${val}`);
		},
		...mapActions('student',['getClassStudentList'])
	},

	computed:{
		...mapState('student',{
			classTable:state => state.classTable
		})
	},

	filters:{
		/**@function 把值转化为对应的名称 
		 * @param {要转为的值} value
		 * @param {类型} type
		*/
		convertValueToName:function(value,type){
			if(!value && value != 0)return '未知';
			let studentStates = [//学生状态
				{ text: '休学', value: 3 }, 
				{ text: '退学', value: 4 }, 
				{ text: '毕业', value: 5 },
				{ text: '正常', value: 2 }];
			let studentRecordStates = [//学生记录状态
				{ text: '3关闭', value: 3 }, 
				{ text: '4历史', value: 4 }, 
				{ text: '正常', value: 2 }];
			switch(type){
				case 'Student'://学生状态
					for(let state of studentStates){
						if(state.value === value){
							return state.text;
						}
					}
					break;
				case 'Record':
					for(let record of studentRecordStates){
						if(record.value === value){
							return record.text;
						}
					}
				default:
					return '未知'
					
			}
		}
	},

	mounted(){
		/**@function  获取学生列表*/
		let url = 'claStu!query2.action';
		let params ={start:0,limit:80};
		this.getClassStudentList({url,params})
	}
}
</script>
<style scoped>
	.postion1{    /*全选按钮和批量删除按钮位置*/
		margin-top:2%;
		float: left;
		margin-left:5%;
	}
	.block{   /* 翻页样式*/
		margin-top:2%;
		float: right;
		margin-right:5%;
	}
	.reset{
		color:#707079;
	}
	.normal{
		color:#1ab55f;
	}
	.abnormal{
		color:#ff7a7b
	}
</style>




<style>
.class-student-table th{
	text-align: center;
	background-color: #E9EEF3;
}
.class-student-table th div{
	height:23px;
	line-height:23px;
}
.class-student-table .el-table__column-filter-trigger{
	line-height:normal;
}
.class-student-table .el-dialog__header{ /* 权限设置dialog头部样式 */
	background-color:rgb(13,113,74);
	font-family:'MicrosoftYaHei';
}
.class-student-table .el-dialog__title{ /* 权限设置dialog头部字体样式 */
	color:white;
	font-size:18px;
}
.class-student-table .el-button--primary{ /* 重设密码按钮确认 */
	width:100px;
	height:35px;
	background-color:rgb(13,113,74);
}
.class-student-table .el-icon-delete{
        color:#ff7a7b;
        font-size:16px;
    }
</style>
