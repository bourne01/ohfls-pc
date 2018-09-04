<template>
    <div class="set-building">
        <el-button type="text" @click="dialogFormVisible = true" class="buildings">设置教学楼</el-button> <!--设置教学楼对话框-->
            <el-dialog title="设置教学楼" :visible.sync="dialogFormVisible" width="40%">
                <el-row>
                     <el-col :span="24">
                         <el-button type="text" @click="addBuilding = true" class="addBuilding">设置教学楼</el-button> <!--设置教学楼对话框-->
                            <el-dialog title="新增教学楼" :visible.sync="addBuilding" width="40%"> <!--新增教学楼对话框-->
                                <el-row>
                                    <el-col :span="24">
                                         <el-form-item label="* 教学楼名称" :label-width="formLabelWidth">  
                                             <el-input v-model="input" placeholder=""></el-input>
                                         </el-form-item>
                                    </el-col>
                                </el-row>
                            </el-dialog>
                     </el-col>
                 </el-row>
            <el-table 
            :data="buildingTable" 
            border 
            style="width: 100%;margin-top:20px"
            max-height="300">
                <el-table-column 
                prop="number" 
                label="教学楼编号" 
                width="150">
                </el-table-column>
                <el-table-column 
                prop="name" 
                label="教学楼名称" 
                width="200">
                </el-table-column>
                <el-table-column 
                prop="TeachingState" 
                label="开启" 
                width="200" >
                </el-table-column>
                <el-table-column 
                fixed="right" 
                label="操作" >
                    <template slot-scope="scope">
                         <el-button @click="handleClick(scope.row)" type="text" size="small">编辑</el-button>
                         <el-button type="text" size="small">删除</el-button>
                    </template>
                </el-table-column>
  </el-table>
            </el-dialog>
    </div>
</template>
<style scoped>
.buildings{ /*设置教学楼*/
    width: 8%;
    height: 30px;
    background: white;
    line-height: 0px;
    border: 1px solid rgb(229,233,236);
    font-family: 'Microsoft YaHei';
    font-size: 16px;
    margin-top: 1.2%;
    float: left;
    margin-left: 2%;
}
.addBuilding{  /*新增教学楼*/
    width: 20%;
    height: 30px;
    background: rgb(91,155,253);
    color: white;
    font-size: 16px;
    font-family: 'Microsoft YaHei';
    line-height: 0px;
    float: left;
    font-size: 16px;
    font-family: 'Microsoft YaHei';
    margin-left: 3%;
}

</style>
<script>
export default {
    data() {
      return {
        input:'',
        dialogFormVisible: false,
        addBuilding:false,
        formLabelWidth: '120px',
         buildingTable: [{
          number: '2016-05-03',
          name: '瓯海区外国语学校第二栋',
          TeachingState: '开启',
        }, {
          number: '2016-05-03',
          name: '瓯海区外国语学校第二栋',
          TeachingState: '开启',
        }, {
          number: '2016-05-03',
          name: '瓯海区外国语学校第二栋',
          TeachingState: '开启',
        },{
          number: '2016-05-03',
          name: '瓯海区外国语学校第二栋',
          TeachingState: '开启',
        },{
          number: '2016-05-03',
          name: '瓯海区外国语学校第二栋',
          TeachingState: '开启',
        },
        ]

      }
    },
}
</script>
<style>
.set-building .el-dialog__title{  /*标题样式*/
    color:white;
    font-size:16px;
    font-family:'Microsoft YaHei';
    float: left;

}
.set-building .el-dialog__header{  /*对话框头部颜色*/
    background-color:rgb(13,113,74);
    height:25px;
}
.set-building .el-dialog__headerbtn .el-dialog__close{ /*对话框右边关闭符号颜色*/
    color:white;
}
.set-building .el-table th{
    background:rgb(246,249,251);
    text-align:center;
    font-family: 'Microsoft YaHei';
}
</style>


