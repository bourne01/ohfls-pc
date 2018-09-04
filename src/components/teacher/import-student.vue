<template>
    <div class="function2">
          <el-button type="text" @click="dialogFormVisible = true" class="importStudent">批量导入</el-button>  <!--这是标题-->
            <el-dialog title="批量导入" :visible.sync="dialogFormVisible" width="25%"> <!--这是标题-->
  <el-form :model="form" >
    <el-form-item label="上传文件" :label-width="formLabelWidth">
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
                <el-button size="small" type="primary" class="uploadPosition">点击上传</el-button>   <!--上传文件按钮-->
         </el-upload>
    </el-form-item>
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
</el-dialog>
 </div>
</template>
<script>
export default {
      data() {
      return {
        
        dialogTableVisible: false,
        dialogFormVisible: false,
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
   width: 130px;
  height: 30px;
  color:rgb(91, 155, 253);
  line-height: 6.5px;
  float: left;
  background: white;
  border: solid 1px rgb(229, 233, 236);
  margin-left: 260px;
  margin-top: -50px; 
}
.uploadPosition{
    margin-right: 135px;
}
.span{
    float: left;
}
.bottom{
    margin-top: 50px;
    margin-right: 145px;
}
</style>

