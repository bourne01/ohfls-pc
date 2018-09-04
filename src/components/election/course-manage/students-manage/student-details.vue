<template>
    <div class="student-details">
        <el-button type="text" 
        size="small"
        class="details" 
        @click="studentDetails = true"
        >详情</el-button> <!--点击查看该学生的详细信息-->
        <el-dialog       
        title="查看详情"
        :visible.sync="studentDetails"
        width="35%"
        :before-close="handleClose">
        <div class="student-message">       <!--学生的详细信息--> 
            <div style="float:left;margin-bottom:5px;">学生信息</div>
            <el-table               
            :data="tableData"
            border
            style="width: 100%;clear:left"
            >       <!--学生的详细信息表格--> 
                <el-table-column
                prop="name"
                label="姓名"
               >
                </el-table-column>
                <el-table-column
                prop="ID"
                label="学号"
                >
                </el-table-column>
                <el-table-column
                prop="gender"
                label="性别">
                </el-table-column>
            </el-table>
        </div>
        <div class="selected-course">
              <div style="float:left;margin-bottom:5px;">所选课程</div>
            <el-table               
            :data="tableData"
            border
            style="width: 100%;clear:left"
            >       <!--所选课程信息的表格--> 
                <el-table-column
                prop="name"
                label="姓名"
                >
                </el-table-column>
                <el-table-column
                prop="type"
                label="类型">
                </el-table-column>   
                <el-table-column
                prop="numbering"
                label="编号"
                >
                </el-table-column>
                 <el-table-column
                prop="credit"
                label="学分"
                >
                </el-table-column>
                 <el-table-column
                prop="status"
                label="状态"
                >
                </el-table-column>
            </el-table>
        </div>
        <div class="teacher-evaluate">
             <div style="float:left;margin-bottom:5px;">所选课程</div>
            <div class="rate">      <!--评分-->
            <el-rate
            v-model="value1"
            :max="3"
            show-text
            :texts="['一般', '良好', '优秀']"
            >
            </el-rate>
            </div>
            <div class="evaluate-time">
                评价时间
            </div>
            <div class="evaluate-content">      <!--里面是具体的评价内容--> 
            
            </div>
        </div>
    </el-dialog>         <!--详细信息的弹框--> 
        </div>
</template>

<script>
export default {
    data(){
        return{
            studentDetails: false,
            value1: null,   
        }
        
    }
}
</script>

<style scoped>
    .details{   
        float: left;
        margin: 3px  -10%  0 30%;
    }
    .student-message{       /*学生信息的样式*/
        margin:0 auto;
        width: 90%;
        margin-top: 35px
      
    }
    .selected-course{       /*学生所选课程的样式*/
        margin:0 auto;
        width: 90%;
        margin-top: 20px
    }
    .rate{
        clear: left;
        float: left;
        height: 40px;
        width: 100px；
    }           /*评分样式*/
    .teacher-evaluate{      /*老师评语的样式*/
       margin:0 auto;
        width: 90%;
        margin-top: 20px;
      
    }
    .evaluate-time{     /*时间的显示位置*/
        float: right;
       
    }
    .evaluate-content{      /**老师具体评价的内容*/
        height: 40px;
        width: 100%;
        background-color: aliceblue;
        float: left;
        clear: left;
        max-height: 80px;
    }
</style>

<style>
.student-details th{  /*表格的样式*/
    color:#707079;
    font-weight: normal;
}
 .student-details .el-dialog  {    /*设置弹框整体的样式*/
   height:620px;
   
  
 }                                                               
 .student-details .el-dialog__header{             /*设置弹框的头部*/
   background-color :#0d714a;
   height: 50px;
   color:#707079; 
   margin:0px;
 }
  .student-details  .el-dialog__header .el-dialog__title{       /*设置头部的标题样式*/ 
    color:white;
    float: left;
    line-height:10px;
    margin-left:20px;
    font-size:16px;
    font-family:'MicrosoftYaHei';
 }
 .student-details .el-dialog__headerbtn .el-dialog__close{      /*设置头部关闭的样式*/
   color:white;
   font-size:24px;
   margin-top:-30px;
 }
 
 .student-details .el-dialog__body {                        /*设置弹框body内的内填充为0*/
    padding:0;  
    width:100%;
}

/* .v-modal{
    background-color:Transparent
} */
 .teacher-evaluate .el-rate__icon.el-icon-star-on{  /*设置评分的大小*/
    font-size:24px;
    }
.teacher-evaluate .el-rate__text{   /*设置评分等级的样式*/
    font-size:14px;
    font-family:'MicrosoftYaHei';
    color:black;
    margin-left:2px;
}
.teacher-evaluate .el-rate__icon.el-icon-star-off{      /*设置评分的大小*/
    font-size:24px;
}
.teacher-evaluate .el-rate__item {          /*设置评分之间的间距*/
    margin-right:10px;
}
</style>

