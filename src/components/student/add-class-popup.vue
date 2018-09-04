<template>
    <div class="add-grade">
        <el-button type="primary" @click="addGrade = true" class="add-grade-button">+ 新增班级</el-button>  <!--打开弹窗的按钮-->  
              <el-dialog
                title="添加班级"
                :visible.sync="addGrade"
                width="35%"
                class="class-dialog"
                >                                     <!--添加班级弹窗-->
              <div>
              <el-transfer
                  filterable
                  :filter-method="filterMethod"
                  filter-placeholder="搜索班级"
                  v-model="value2"
                  :data="data2"
                  :titles="['班级列表','班级列表(已选)']">
                </el-transfer>                                          <!--穿梭框-->
                <div class="footer">                    
                    <el-button type="primary" class="confirm-button">确定添加班级</el-button>      <!--确定添加按钮-->
                </div>
              </div>
              </el-dialog>


    </div>
</template>

<script>
   export default {
    data() {
      const generateData2 = _ => {
        const data = [];
        const classes = ['一（1）班','二（1）班','三（1）班','四（2）班','五（2）班','六（1）班',];
        const pinyin = ['yi','er','san','si','wu','liu'];
        classes.forEach((clas, index) => {
          data.push({
            label: clas,
            key: index,
            pinyin: pinyin[index]
          });
        });
        return data;
      };
      return {
         addGrade:false,
         data2: generateData2(),
        value2: [],
        filterMethod(query, item) {
          return item.pinyin.indexOf(query) > -1;
        }
       
      };
    }
  };
</script>



<style scoped>
    .add-grade-button{                /*打开弹窗按钮的样式*/  
        float: left;
        height: 26px;
        width: 15%;
        line-height: 0;
        margin: 2.5% 0 0 5%;
        
    }
    .footer{      /*弹窗脚部的样式*/
      width: 100%;
      height: 48px;
      background-color: #E9EEF3;
    }
    .confirm-button{        /*确定添加班级按钮的样式*/
      float: right;
      height: 29px;
      line-height: 30%;
      width: 22%;
      margin: 10px 2% 0 0;
      text-align: center;
    }
      @media (max-width:1440px){    /*媒体查询样式*/
		    .confirm-button{
				font-size:12px;
			}
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
    padding:0;  
    width:100%;
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

