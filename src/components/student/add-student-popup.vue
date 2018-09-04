<template>
    <div class="add-student">
       <div class="function">
          <el-button type="text" @click="addStudent" class="addStudentFunction">+ 新增学生</el-button>  <!--新增教师按钮-->
       </div>
       <!-- 新增学生按钮-->
        <el-dialog title="新增学生" :visible.sync="dialogTableVisible" width="37%"> <!--新增教师标题--> <!--教师栏-->
            <el-form :model="studentForm" ref="studentForm"   label-position="right" :inline="true">
                <el-form-item label="姓名" prop="name"  size="mini" >
                  <el-input v-model="studentForm.name"></el-input>
                </el-form-item>
                 <el-form-item label="学籍" prop="id"  size="mini" >
                  <el-input v-model="studentForm.id"></el-input>
                </el-form-item>
               <el-form-item label="性别" prop="gender" >
                    <el-select v-model="studentForm.region" placeholder="请选择性别" size="mini" >
                      <el-option label="男" value="man" ></el-option>
                      <el-option label="女" value="woman"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="学分" prop="score" >
                  <el-input v-model="studentForm.score"></el-input>
                </el-form-item>
                <el-form-item label="年级" prop="grade" >
                    <el-select v-model="studentForm.region" placeholder="请选择年级" size="mini" >
                      <el-option label="男" value="man" ></el-option>
                      <el-option label="女" value="woman"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="班级" prop="gender" >
                    <el-select v-model="studentForm.region" placeholder="请选择班级" size="mini" >
                      <el-option label="男" value="man" ></el-option>
                      <el-option label="女" value="woman"></el-option>
                    </el-select>
                </el-form-item>
                 <el-form-item label="备注" prop="summary" >
                    <el-input type="textarea" class="textarea"></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="onSubmit">提交</el-button>
                  </el-form-item>
            </el-form>
        </el-dialog>
         </div>

</template>
<script>

  export default {
    data() {
      return {
        labelPosition: 'top',
        studentForm:{
          name:'',
        },
        labelPosition: 'right',
        dialogTableVisible: false,
        dialogFormVisible: false,
        multipleSelection: [],
        formLabelWidth: '120px'
      }
    },
    methods: {
      toggleSelection(rows) {   /* 全选按钮*/
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
     addStudent(){ /* 新增教师点击按钮*/
       console.log('addStudent')
       this.dialogTableVisible = true;
      },
       onSubmit() {
        console.log('submit!');
      }
    }
  } 

</script>
<style scoped>



.addStudentFunction{ /* 添加教师功能按钮样式 */
  width: 12%;
  height: 28px;
  background:rgb(91, 155, 253);
  color:#fff;
  line-height: 0%;
  float: left;
  margin: 2% 0 0 5%;
}
.textarea{
  width: 540px;
}
</style>
<style>
.list th{       /*  表头字体居中 */
  text-align: center;
  background-color: #E9EEF3;
}
.add-student .el-dialog__header{ /* 这是新增标题头部样式*/
  background-color:rgb(13,113,74);
  height: 30px;
 }
.add-student .el-dialog__title{  /* 新增学生标题样式*/
  color:white;
  font-size:18px;
  float: left;
    font-family:'MicrosoftYaHei';
}
.add-student .el-button--primary{
  width:100px;
  height:35px;
  line-height:50%;
  background-color:rgb(13,113,74);
  clear: left;
}
.add-student .el-input__inner{   
  height: 35px;
  background:#f6f9fb;
  width: 240px;
  clear: both;
}
.add-student .el-textarea__inner{ /*区域框背景色*/
   background:#f6f9fb;
}
.add-student .el-dialog__headerbtn .el-dialog__close{ /*关闭颜色*/
  color:white;
}
.add-student .el-dialog{
  height:auto
}
</style>