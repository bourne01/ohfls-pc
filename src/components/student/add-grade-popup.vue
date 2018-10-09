<template>
    <div class="add-grade">		
		<el-dialog
			title="添加班级"
			:visible.sync="isShow"
			width="70%"	
			class="grade-dialog"
			>
			<div class="toolbar">
				<el-button icon="el-icon-plus">新增年级</el-button>     
			</div>
			<el-table 
				:data="gradeList"
				max-height="625"
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
					prop="state"
					label="编号"
					max-width="150">
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
						<el-button type="text" >编辑</el-button>
						<el-button
						@click.native.prevent="deleteRow(scope.$index, tableData)"
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

    </div>
</template>

<script>
export default {
	props:['is-pop'],
    data() {    
		return {
			gradeList:[],//年级列表				
		};
	},
	computed:{
		isShow:{
			get:function(){
				return this.isPop;
			},
			set:function(val){}
		}
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
</style>

<style>
  .add-grade .el-dialog  {    /*设置弹框整体的样式*/
   height:56.5vh;
  
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
</style>

