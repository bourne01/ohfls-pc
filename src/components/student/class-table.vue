<template>
    <div class="class-table">
       <!--这是新增班级表格左边部分-->
        <el-table 
            :data="gradeTable"
            style="width:100%;margin-left:20px;"
            max-height="625"
            border
            :span-method="objectSpanMethod"
            >  <!-- ←←←←←这是表格列合并-->
            <el-table-column                
            label="年级"
            max-width="120">
            <template slot-scope="scope">
                {{scope.row.claGradeNm}}
            </template>
            </el-table-column>
            <el-table-column
            prop="claNO"
            label="班级编号"
            max-width="150">
            </el-table-column>
            <el-table-column
            prop="claName"
            label="班级"
            max-width="160">
            </el-table-column>
            <el-table-column
            label="操作"
            max-width="180">
            <template slot-scope="scope">   <!--这是操作区域功能-->
                <el-button type="text" >编辑</el-button>
                <el-button
                @click.native.prevent="deleteRow(scope.$index, tableData)"
                type="text"
                size="small"
                icon="el-icon-delete">                
                </el-button>
            </template>
            </el-table-column>
            </el-table>
            <div class="block">    <!--这是翻页功能-->
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page.sync="currentPage1"
                    :page-size="10"
                    layout="total, prev, pager, next"
                    :total="100"
                    style="margin-top:20px;">
                </el-pagination>
        </div> 
    </div>
</template>
<script>
import {mapActions,mapState} from 'vuex'
  export default {
    methods: {
      deleteRow(index, rows) {      /*删除方法*/
        rows.splice(index, 1);
      }
    },
    data() {
      return {
        currentPage1:1,
      
      }
    },
    methods:{
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
        },
        objectSpanMethod({ row, column, rowIndex, columnIndex }){
            if (columnIndex ===0){   //判断条件可以设置成你想合并的列的起始位置
                if(rowIndex % 4 ===0){//判断条件可以设置成你想合并的行的起始位置
                    return{
                        rowspan:4,
                        colspan:1,
                    };
                }else{
                    return{
                        rowspan:0,
                        colspan:0,
                }
            }
        } 
    },
    ...mapActions('student',['getClassTable'])
    },
    computed:{
        ...mapState('student',{
            gradeTable:state => state.gradeTable
        })
    },
    mounted(){
        /**获取班级列表 */
        let params ={start:0};
        this.getClassTable(params)
    }
        
}
</script>

<style scoped>
    .block{  /* 翻页功能样式 */
        float: left;
        margin-left:9%;
        margin-top: 2%;
    }
    .leftTable{
        clear:left;
        float: left;
    }
    
</style>
<style>
    .class-table th{       /*  表头字体居中 */
        text-align: center;
        background-color: #E9EEF3;
        font-family:'MicrosoftYaHei';
    }
    .class-table .el-icon-delete{
        color:#ff7a7b;
        font-size:16px;
    }
</style>

