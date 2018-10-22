<template>
  	<div class="evaluate-student">    
		<el-button type="text" @click="isShow=true" 		
		>                                                     <!--评价学生的文字按钮-->
		{{evaluateTxt}}</el-button>   <!--点击弹出评价对话框-->
		<el-dialog
			title="评价学生"
			:visible.sync="isShow"
			:modal="false"
			width="35%">
			<div class="student-name">      <!--学生姓名-->
			姓名：<div style="color:black;float:right">{{student.stuName}}</div> 
			</div>
			<div class="student-ID"> <!--学生的学号-->
			学号：<div style="color:black;float:right">{{student.stuNO}}</div> 
			</div>
			<div class="evaluate-rate"> <!--评定的星级-->
				<div style="float:left;margin-bottom:5px;">评价学生</div>
				<el-rate
					v-model="credit"
					:max="3"
					show-text
					:texts="[ '良好', '优秀','特优']"
					style="float:left;clear:left"
					>
					</el-rate>
			</div>
			<div class="score">        <!--评定的学分--> 
			<div style="float:left">取得分</div>
			<input type="text" class="get-score" disabled v-model="credit">   <!--评分的输入框--> 
			</div>
			<div class="comment">   <!--评语内容-->
			<div  style="float:left;margin-bottom:10px">评语内容</div>
			<textarea  class="comment-txt" v-model="evaluateContent"></textarea> <!--写评语内容的文本域-->
			</div>
			<div class="submit-evaluate">         <!--提交评价的按钮-->
				<el-button class="submit" @click="submit">提交评价</el-button>
			</div>
		</el-dialog>
  	</div>
</template>



<script>
import { evaluateStudent } from '../../../../api/election.js'
export default {
    props:["student"],
    data() {
      return {
        isShow: false,
		evaluateContent:'',
		credit:null,
      };
    },
    computed:{
      	evaluateTxt:function(){
			  console.log(this.student);
			let evaluateText = '';
			switch(this.student.evalStu){//2未评(默认) 4普通 6满意 8很好
				case 8:
					evaluateText = '很好';
					break;
				case 6:
					evaluateText = '满意';
					break;
				case 4:
					evaluateText = '普通';
					break;
				default:
					evaluateText = '评价学生';
			}	
			return evaluateText;
      }
    },
    methods: {
		/**
		 * @function 监听提交点击事件，向服务器提交对学生评语
		 */
		submit(){
			let tearchEvaluationCode = 2;//老师评价 系统代码81 2未评(默认) 4差评 6及格 8良好 10优秀
			switch(this.credit){
				case 1:
					tearchEvaluationCode = 6;
					break;
				case 2:
					tearchEvaluationCode = 8;
					break;
				case 3:
					tearchEvaluationCode = 10;
					break;
				default:
					this.$message.error('未知类型评价代码')
			}
			let params = {
				stuCouId:this.student.stuCouId,
				evalStu:tearchEvaluationCode,
				evalStuTxt:this.evaluateContent
			};
			evaluateStudent(params);
		},
    }
  };
</script>
<style scoped>
	.evaluate-student .el-button{
		font-size:12px;
	}
	.student-name{    /*姓名的样式*/
		float: left;
	}
	.student-ID{    /*学号的样式*/
		float:left;
		margin-left: 12%
	}
	.evaluate-rate{ /*评定等级的样式*/
		clear: left;
		float: left;
		margin-top: 50px;
		height: 50px;
	
	}
	.score{       /*评学分的样式*/
		float: right;
		height: 50px;
		margin-top: 50px
	}
	.get-score{     /*分数输入框的样式*/
		clear: left;
		float: left;
		background-color: #f6f9fb;
		border: 1px solid #e5e9ec;
		border-radius: 4px;
		height: 26px;
		margin-right: 7.2%;
		padding-left: 15%;
		width: 80%;
	}
	.comment{     
		clear: left;
		float: left;
		margin-top: 50px;
		width: 100%;
		
	} 
	.comment-txt{       /*评价内容文本框的样式*/
		clear: left;
		float: left;
		height: 70px;
		width: 100%;
		background-color: #f6f9fb;
		border: 1px solid #e5e9ec;
		border-radius: 4px;
	
	}
  .submit{      /*按钮样式*/
    color: white;
    clear: left;
    float: left;
    width: 27%;
    height: 40px;
    font-size: 16px;
    background-color: #0d714a;
    border: none;
    cursor: pointer;
    border-radius: 4px;
    margin-top: 65px;
  }
</style>


<style>
.student-details th{  /*表格的样式*/
    color:#707079;
    font-weight: normal;
}
 .evaluate-student .el-dialog  {    /*设置弹框整体的样式*/
   height:600px;
   
}
 .evaluate-student .el-dialog__body {                        /*设置弹框body内的内填充*/
    padding:12% 8%  0  8%;  
    width:100%;
    
}
 .evaluate-rate .el-rate__icon.el-icon-star-on{  /*设置评分的大小*/
    font-size:24px;
    }
.evaluate-rate .el-rate__text{   /*设置评分等级的样式*/
    font-size:14px;
    font-family:'MicrosoftYaHei';
    color:black;
}
.evaluate-rate .el-rate__icon.el-icon-star-off{      /*设置评分的大小*/
    font-size:24px;
}
.evaluate-rate .el-rate__item {          /*设置评分之间的间距*/
    margin-right:6px;
}
</style>



