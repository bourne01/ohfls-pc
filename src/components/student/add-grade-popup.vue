<template>
    <div class="add-grade">		
		<el-dialog
			title="设置班级"
			:visible.sync="isShow"
			width="50%"				
			class="grade-dialog"
			border
			@close="onClose"
			>
			<div class="toolbar">
				<el-button icon="el-icon-plus" @click="isAddGrade=true">新增年级</el-button>     
			</div>
			<el-table 
				:data="gradeList"
				max-height="400"
				border
				class="grade-table"
				>  <!-- ←←←←←这是表格列合并-->
				<el-table-column
					prop="name"                
					label="年级"
					max-width="120">				
				</el-table-column>
				<el-table-column
					prop="nO"
					label="编号"
					max-width="150">
				</el-table-column>				
				<el-table-column
					label="状态"
					max-width="150">
					<template slot-scope="scope">
						{{scope.row.state|convertValueToName}}
					</template>
				</el-table-column>				
				<el-table-column
					prop="remark"
					label="备注"
					max-width="150">
				</el-table-column>				
				<el-table-column
					label="操作"
					max-width="180">
					<template slot-scope="scope">   <!--这是操作区域功能-->
						<el-button type="text" 
						@click.native.prevent="editGrade(scope.row)"
						>编辑</el-button>
						<el-button
						@click.native.prevent="deleteGrade(scope.$index, scope.row)"
						type="text"
						size="small"
						icon="el-icon-delete">                
						</el-button>
					</template>
				</el-table-column>
				</el-table>
				<div class="block">    <!--这是翻页功能-->
					<!-- <el-pagination
						@size-change="handleSizeChange"
						@current-change="handleCurrentChange"
						:current-page.sync="currentPage1"
						:page-size="10"
						layout="total, prev, pager, next"
						:total="100"
						style="margin-top:20px;">
					</el-pagination> -->
				</div> 
		</el-dialog>
		<el-dialog
			:title="title"
			:visible.sync="isAddGrade"
			width="25%"				
			class="grade-dialog"
			border
			@close="isAddGrade=false"
			>
			<ul>
				<li>
					<label for="">名称</label>
					<el-input v-model="grade.name"></el-input>
				</li>
				<li>
					<label for="">编号</label>
					<el-input v-model="grade.NO"></el-input>
				</li>
				<li>
					<label for="">状态</label>
					<el-select v-model="grade.state"></el-select>
				</li>				
				<li>
					<label for="">备注</label>
					<el-input v-model="grade.remark" type="textarea"></el-input>
				</li>
				<li>
					<label for=""></label>
					<el-button class="confirm" @click="addGrade">确定</el-button>
				</li>
			</ul>
		</el-dialog>
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import { delSysCode, editSysCode, addSysCode }  from '../../api/public.js'
export default {
	props:['is-pop'],
    data() {    
		return {
			//gradeList:[],//年级列表	
			grade:{},
			title:'添加年级',
			isAddGrade:false,//控制编辑或添加对话框
			isEdit:false,//判断当前是否编辑模式		
		};
	},
	computed:{
		isShow:{
			get:function(){
				return this.isPop;
			},
			set:function(val){}
		},
		...mapState('student',{
			gradeList:state => state.gradeTable,
		})
	},
	methods:{
		...mapActions('student',['getGradeTable']),

		/**@function 向父组件传递关闭对话框事件 */
		onClose(){
			this.$emit('close-pop');
		},
		
		/**@function 向服务器提交新增年级 */
		addGrade(){
			this.isAddGrade = false;
			this.isEdit = false;
			this.title = "添加年级";
			let params = {
				nO:this.grade.NO,
				name:this.grade.name,
				state:this.grade.state,
				remark:this.grade.remark,
				};
			if(this.isEdit){
				editSysCode(params);
				
			}else{
				addSysCode(params)
			}
			
		},

		/**@function 向服务器提交新增年级 
		 * @param {行数据} row
		 */
		editGrade(row){
			this.isAddGrade = true;
			this.title = "编辑年级";
			this.isEdit = true;
			this.grade = {
				name:row.name,
				NO:row.nO,
				state:row.state,
				remark:row.remark
			}
		},

		/**
		 *@function 删除年级
		 *@param {在当前表格中的行序} rowIndex
		 *@param {行数据} row
		 */
		 deleteGrade(rowIndex,row){			 
			 let params = {code:row.code,type:35,state:4};
			 editSysCode(params)
			 	.then(res => {
					 if(res.data.success){
						 params.state = undefined;
						 delSysCode(params)
			 				.then(res => {
								if(res.data.success)
									this.$message.success(res.data.message);
								else{
									this.$message.error('删除年级失败！')
								}
				 			})
					 }
				 })
			 
		 }
	},

	filters:{
		/**@function 把值转化为对应的名称 
		 * @param {要转为的值} value
		*/
		convertValueToName:function(value){
			if(!value && value != 0)return '未知';
			let gradeStates = [//学生状态
				{ text: '关闭', value: 3 }, 
				{ text: '历史', value: 4 }, 
				{ text: '正常', value: 2 }];	
			for(let state of gradeStates){
						if(state.value === value){
							return state.text;
						}
					}	
			
		}
	},
	mounted(){
		let params = {type:35,start:0,limit:50};
		this.getGradeTable(params);
	}
  };
</script>



<style scoped>	
	.toolbar{
		height:70px;
		line-height:70px;
		text-align:left;
	}
	.toolbar .el-button{
		height:32px;
		/* line-height:32px; */
		padding-top:0;
		padding-bottom:0;
		background-color:#409EFF;
        color:#fff;
        font-size:14px;   
	}
	.grade-dialog ul{
		width:75%;
		margin:35px auto;		
	}
	.grade-dialog li{
		display: flex;
		align-items: center;
	}
	.grade-dialog li>label{
		display: inline-block;
		width:45px;
	}
	.grade-dialog li{
		margin-bottom:25px;
	}
	.grade-dialog .el-select{
		width:100%;
	}
	.grade-dialog .confirm{
		height:32px;
		/* line-height:32px; */
		padding:0 40px;
		background-color:#0d714a;
        color:#fff;
        font-size:14px;  
	}
</style>

<style>
	.add-grade th{       /*  表头字体居中 */
        text-align: center;
        background-color: #E9EEF3;
        font-family:'MicrosoftYaHei';
    }
	.grade-table td{
		padding:5px;
	}
	.grade-table .el-icon-delete{
        color:#ff7a7b;
        font-size:16px;
    }
	.add-grade .el-dialog  {    /*设置弹框整体的样式*/
		
	}                                                               
	.add-grade .el-dialog__header{             /*设置弹框的头部*/
		background-color :#0d714a;
		height: 20px;
		color:#707079; 
		margin:0px;
	}
	.add-grade .el-dialog__header .el-dialog__title{       /*设置头部的标题样式*/ 
		color:white;
		float: left;
		line-height:10px;
		margin-left:20px;
		font-size:16px;
		font-family:'MicrosoftYaHei';
	}
	.add-grade .el-dialog__headerbtn .el-dialog__close{      /*设置头部关闭的样式*/
		color:white;
		font-size:24px;
		margin-top:-30px;
	}
	.add-grade .el-dialog__body {                        /*设置弹框body内的内填充为0*/
		padding:20px;  
		padding-top:10px;
		width:100%;
		box-sizing:border-box;
	}
	.add-grade .el-transfer-panel__item.el-checkbox .el-checkbox__label{        /*设置复选框的布局*/
		display:inline;
	}
	.add-grade .el-transfer-panel__item{      /*设置复选框文字的布局*/
		display: table;

	}
	.add-grade .el-transfer-panel{      /*设置穿梭框之间的宽度*/
		width:44%;
		height:46.5vh;
		
	}
	.add-grade .el-transfer__buttons{         
		padding: 0 1.2%
	}
	.add-grade .el-checkbox-group.el-transfer-panel__list.is-filterable{  /*设置滚动条*/
		overflow-y:scroll;
		height: 40vh;
	}
	.add-grade .el-transfer-panel__header{    /*隐藏自带的头部*/
		display: none;
	}
	.add-grade .el-transfer-panel__body{    /*设置body的高为100%*/
		height: 100%;
	}
	.grade-dialog .el-input__inner{
		background-color:#f6f9fb;
		height:32px;
		line-height:32px;
		box-sizing: border-box;
		padding-top:0;
		padding-bottom:0;
	}
	.grade-dialog .el-textarea__inner{
		background-color:#f6f9fb;
	}
</style>

