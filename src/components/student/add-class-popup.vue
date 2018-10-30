<template>
    <div class="add-class">
		<el-dialog
			:title="title"
			:visible.sync="isShow"
			width="25%"				
			class="class-dialog"
			border
			@close="$emit('close-pop')"
			>
			<ul>
				<li>
					<label for="">名称</label>
					<el-input v-model="class_.name"></el-input>
				</li>
				<li>
					<label for="">编号</label>
					<el-input v-model="class_.NO"></el-input>
				</li>
				<li>
					<label for="">年级</label>
					<el-select v-model="class_.grade" placeholder="请选择年级">
						<el-option
							v-for="item in gradeList"
							:key="item.id"
							:label="item.name"
							:value="item.id">
						</el-option>
					</el-select>
				</li>				
				<li>
					<label for="">备注</label>
					<el-input v-model="class_.remark" type="textarea"></el-input>
				</li>
				<li>
					<label for=""></label>
					<el-button class="confirm" @click="confirm">确定</el-button>
				</li>
			</ul>
		</el-dialog>

    </div>
</template>

<script>
import { getSelector } from '../../api/public';
import { addClass, editClass, getClasses } from '../../api/student';
import { xhrErrHandler } from '../../utils/util';
import { mapActions } from 'vuex';
export default {
	props:['is-pop','class-id'],
    data(){
		return{
			title:'新增班级',
			class_:{},//班级对象
			gradeList:[],//年级列表
		}
	},
	computed:{
		isShow:{
			get:function(){
				return this.isPop;
			},
			set:function(val){}
		}
	},
	methods:{
		/**
		 * @function 监听确定按钮事件，执行新增或修改班级信息
		 */
		confirm(){
			let params = {
				claNO:this.class_.NO,
				claName:this.class_.name,
				claGrade:this.class_.grade,
				remark:this.class_.remark
			}
			let action = addClass
			if(this.classId){//编辑状态
				action = editClass;
				params.claId = this.classId;
			}
			action(params)
				.then(res => {
					if(res.data.success){
						this.$message.success('操作成功！')
						this.getClassTable({});
					}else if(res.data.type === 1){
						this.$message.error(res.data.message)
					}else{
						this.$message.error('发生未知错误，请联系管理员！')
					}
				})
				.catch(err => {
					xhrErrHandler(err,this.$router,this.$message);
				})
			this.$emit('close-pop');
		},
		...mapActions('student',['getClassTable'])
	},
	watch:{
		classId:function(){
			getClasses({claId:this.classId})
				.then(res => {
					if(res.data.success){
						this.class_ = {};//初始化对象
						let class_ = res.data.dataList[0];
						this.class_.name = class_.claName;
						this.class_.NO = class_.claNO;
						this.class_.grade = class_.claGrade;
						this.class_.remark = class_.remark;
					}
				})
				.catch(err => {
					xhrErrHandler(err,this.$router,this.$message)
				})
		}
	},
	created(){
		/**
		 * @function 获取年级列表
		 */
		let params = {
			f:'uxCode',
			codeType:35,
			simple:0
		};
		getSelector(params)
			.then(res => {
				this.gradeList = res.data.dataList;				
			}) 
	},
	mounted(){
	}
  };
</script>



<style scoped>
    .class-dialog ul{
		width:75%;
		margin:35px auto;		
	}
	.class-dialog li{
		display: flex;
		align-items: center;
	}
	.class-dialog li>label{
		display: inline-block;
		width:45px;
	}
	.class-dialog li{
		margin-bottom:25px;
	}
	.class-dialog .el-select{
		width:100%;
	}
	.class-dialog .confirm{
		height:32px;
		/* line-height:32px; */
		padding:0 40px;
		background-color:#0d714a;
        color:#fff;
        font-size:14px;  
	}
</style>

<style>
  .add-class .el-dialog__header{             /*设置弹框的头部*/
		background-color :#0d714a;
		height: 20px;
		color:#707079; 
		margin:0px;
	}
	.add-class .el-dialog__header .el-dialog__title{       /*设置头部的标题样式*/ 
		color:white;
		float: left;
		line-height:10px;
		margin-left:20px;
		font-size:16px;
		font-family:'MicrosoftYaHei';
	}
	.add-class .el-dialog__headerbtn .el-dialog__close{      /*设置头部关闭的样式*/
		color:white;
		font-size:24px;
		margin-top:-30px;
	}
	.add-class .el-dialog__body {                        /*设置弹框body内的内填充为0*/
		padding:20px;  
		padding-top:10px;
		width:100%;
		box-sizing:border-box;
	}
</style>

