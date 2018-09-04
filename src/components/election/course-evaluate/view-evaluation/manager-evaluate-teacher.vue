<template>
    <div class="m-e-t">
       <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
            <div class="avatar">头像</div>
            <div class="evaluate-teacher">
                <div class="e-t-text">评价教师</div>
                <el-rate
                    v-model="value1"
                    :max="3"
                    show-text
                    :texts="['一般', '良好', '优秀']"
                >
                </el-rate>
            </div>   
        <!-- <div class="textarea-area">
            <div class="e-t-text">评价教师</div>
           <textarea cols="90" rows="8" class="evaluate-textarea"></textarea>
        </div>  -->
       <el-form-item label="活动形式" class="textarea-area">
    <el-input v-model="formLabelAlign.type" type="textarea"></el-input>
  </el-form-item>
        <el-input type="submit" class="submit-evaluate" value="提交评价"></el-input>
        </el-form>
    </div>
</template>



<script>
 export default {
    data() {
      return {
        value1: null,   
        labelPosition: 'top',
        formLabelAlign: {
        name: '',
        region: '',
        type: ''
        }

      }
    }
  }
</script>



<style scoped>
    .avatar{
        width: 95px;
        height: 95px;
        background-color: aqua;
        border-radius: 50%;
        border:2px solid white;
        background-size:50px;
        float: left;
        margin-left: 8%;
        margin-top: 60px;
    } 
    
    .evaluate-teacher{
        height:60px;
        line-height:10px;
        margin:82px 0 0 40px;
        float: left;
    }
    .e-t-text{
        float: left;
        font-size:12px;
        color:#707079;
        margin-bottom: 12px;
    }
    .el-rate{
        clear: left;
        float: left;
        height: 40px;
    }           /*评分样式*/
    

    .textarea-area{
        clear: left;
        float: left;
        margin-left:203px;
        height: 88px;
        width: 100%
    }
    
    .submit-evalutate{
        clear: left;
        float: left;
        margin-left: 210px;
        margin-top: 100px;
        height: 36px;
        width: 144px;
        color:white;
        background-color: #0d714a;
        border: none;
        cursor: pointer;
        border-radius: 4px;
        font-size: 16px;

    }
    .submit-evaluate{
        clear: left;
        float: left;
        width: 144px;
        margin:60px  0   0 205px; 
        font-size: 16px;
        
    }
</style>


<style>
    .m-e-t .el-rate__icon.el-icon-star-on{  /*设置评分的大小*/
        font-size:28px;
    }
    .m-e-t .el-rate__text{   /*设置评分等级的样式*/
        font-size:16px;
        font-family:'MicrosoftYaHei';
        color:black;
        margin-left:5px;
    }
    .m-e-t .el-rate__icon.el-icon-star-off{      /*设置评分的大小*/
        font-size:28px;
    }
    .m-e-t .el-rate__item {          /*设置评分之间的间距*/
        margin-right:15px;
    }
    .m-e-t .el-form--label-top .el-form-item__label{        
        float: left;
        padding:0
    }
    .m-e-t .el-form-item__label{        /*文本域上面的标签样式，定义该标签的*/
        font-size:12px;
        color:#707079;
      
    }
    .m-e-t .el-textarea__inner{     /*文本域的样式*/
        border: 1px solid #e5e9ec;
        background-color: #f6f9fb;
        border-radius: 2px;
        width: 65%;
        height:88px;
        font-size:12px;
        font-family:'SimSun'
    }
    .m-e-t .el-input__inner{        /*提交按钮的背景颜色和文字颜色*/
        background-color:#0d714a ;
        color: #ffffff;      
        cursor: pointer;
    }
    .m-e-t {
        
    }
</style>






