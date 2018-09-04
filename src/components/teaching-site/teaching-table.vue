<template>
     <div class="teaching-table">    <!--这是教学场地表格-->
            <el-table
            :data="teachingTable"
            ref="multipleTable"
            border
            max-height="800"
            style="width: 100%;">
            <el-table-column
            type="selection"
            width="55">
            </el-table-column>
            <el-table-column
            prop="buiNO"
            label="教学楼编号"
            width="200">
            </el-table-column>
            <el-table-column
            prop="buiName"
            label="教学楼名称"
            width="300">
            </el-table-column>
            <el-table-column
            prop="romNO"
            label="教室编号"
            width="150">
            </el-table-column>
            <el-table-column
            prop="romName"
            label="教室名称"
            width="250">
            </el-table-column>
            <el-table-column
            prop="romType"
            label="教室类型"
            width="170">
            </el-table-column>
            <el-table-column
            prop="state"
            label="教室状态"
            width="170">
            </el-table-column>
            <el-table-column
            prop="remark"
            label="备注"
            width="180">
            </el-table-column>
            <el-table-column
                label="操作">
                <template slot-scope="scope">   <!--这是操作区域功能-->
                <el-button type="text" >编辑</el-button>
                <el-button
                @click.native.prevent="deleteRow(scope.$index, tableData)"
                type="text"
                size="small">
                移除
                </el-button>
                </template>
                </el-table-column>
            </el-table>
             <div class="button">
                <el-button @click="toggleSelection(teachingTable)" >全选</el-button>
                <el-button @click="toggleSelection()">批量删除</el-button>
             </div>
             <div class="block"><!--这是翻页功能-->
                    <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page.sync="currentPage1"
                    :page-size="10"
                    layout="total, prev, pager, next"
                    :total="100">
                    </el-pagination>
            </div>
     </div>
</template>
<script>
import {mapActions,mapState} from 'vuex'
export default {
    data() {
      return {
          currentPage1:5,
      }
    },
    methods: {
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      }, handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      },
      ...mapActions('site',['getTeachingSite'])
    },
    computed:{
        ...mapState('site',{
            teachingTable:state => state.teachingTable
        })
    },
    mounted(){
        let url = 'rom!query2.action';
        let params = {start:0};
        this.getTeachingSite({url,params})
    }

}

</script>
<style scoped>
.button{ /*这是全选、删除按钮样式*/
    margin-top:1%;
    float: left;
    margin-left: 2%;
}
.block{ /*这是翻页样式*/
    float: right;
    margin-top: 1%;
    margin-left: 2%;
}
</style>
<style>
.teaching-table th{ /*表格表头居中*/
  text-align: center;
  background-color: #E9EEF3;
  font-family:'MicrosoftYaHei';
}
.teaching-table .el-dialog__header{
  background: #E9EEF3;    
}
</style>



