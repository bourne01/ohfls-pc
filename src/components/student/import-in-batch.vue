<template>
    <div class="import-in-batch">
           <el-button type="text" @click="dialogFormVisible = true" class="importStudent">批量导入</el-button>  <!--这是标题-->
             <el-dialog title="批量导入学生" :visible.sync="dialogFormVisible" width="25%"> <!--这是标题-->
                <el-row>
                    <el-col :span="24">
                      <el-form :model="form">
                        <el-form-item label="选择文件" :label-width="formLabelWidth">
                           <el-upload        
                            class="upload-demo"
                            action="https://jsonplaceholder.typicode.com/posts/"
                            :on-preview="handlePreview"
                            :on-remove="handleRemove"
                            :before-remove="beforeRemove"
                            multiple
                            :limit="3"
                            :on-exceed="handleExceed"
                            :file-list="fileList">
                            <el-button size="small" type="primary" class="uploadPosition">+ 上传文件</el-button>   <!--上传文件按钮-->
                           </el-upload>
                        </el-form-item>
                      </el-form>  
                    </el-col>
                </el-row> 
                <el-row>
                  <el-col :span="24">
                     <el-form :model="form" >
                       <el-form-item label="数据覆盖" :label-width="formLabelWidth"><!--这是一个下拉菜单-->
                         <el-select v-model="form.region" placeholder="">
                            <el-option label="允许" value="shanghai"></el-option>
                            <el-option label="不允许" value="beijing"></el-option>
                         </el-select>
                       <div class=“fontSize” style="font-size:5px;">注：当数据出现重复时，是否覆盖原有数据</div> <!--这是一个span-->
                       <el-checkbox v-model="checked" class="span">班级编号不存在时创建班级</el-checkbox>
                       <el-button type="primary" @click="dialogVisible = false" class="bottom">导入</el-button>
                       </el-form-item>
                     </el-form>
                  </el-col>
                </el-row>
               </el-dialog>
 </div>
</template>
<script>
export default {
      data() {
      return {
        beforeRemove:'',
        checked:'',
        fileList:[],
        handleExceed:'',
        dialogTableVisible: false,
        dialogFormVisible: false,
        handleRemove:'',
        handlePreview:'',
        form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        formLabelWidth: '120px'
      };
    }
  };
</script>
<style scoped>
.importStudent{ /*批量导入按钮样式 */
  width: 10%;
  height: 30px;
  color:rgb(91, 155, 253);
  line-height: 0%;
  float: left;
  background: white;
  border: solid 1px rgb(229, 233, 236);
  margin: 2% 0 0 3%;
}
.uploadPosition{ /*dialog上传文件按钮样式 */
    margin-right: 135px;
}
.span{
    float: left;
}
.bottom{  /*dialog导入按钮样式 */
    margin-top: 10%;
    margin-right: 35%;
}
</style>
<style>
.import-in-batch .el-dialog__header{
  background-color:rgb(13,113,74);
  font-family:'MicrosoftYaHei';
  font-size:16px;
  color:white;
  height: 30px;
}
.import-in-batch .el-button--primary{
  background-color:rgb(13,113,74);
}
.import-in-batch .el-dialog__title{
  color:white;
  float: left;
}
</style>

