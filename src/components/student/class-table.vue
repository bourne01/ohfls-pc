<template>
    <div class="class-table">
       <!--这是新增班级表格左边部分-->
        <el-table 
            :data="classTable"
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
                <el-button type="text" @click="onEditClick(scope.row.claId)">编辑</el-button>
                <el-button
                @click.native.prevent="deleteClass(scope.row.claId)"
                type="text"
                size="small"
                icon="el-icon-delete">                
                </el-button>
            </template>
            </el-table-column>
            </el-table>
            <div class="block">    <!--这是翻页功能-->
                <el-pagination
                    @current-change="handleCurrentChange"
                    :page-size="pageSize"
                    layout="total, prev, pager, next"
                    :total="total"
                    style="margin-top:20px;">
                </el-pagination>
        </div> 
    </div>
</template>
<script>
import {mapActions,mapState} from 'vuex'
import { editClass, delClasses } from '../../api/student';
import { xhrErrHandler } from '../../utils/util';
  export default {
    data() {
      return {
        pageSize:20,
        total:1,
      
      }
    },
    methods:{
        /**
         * @function 监听点击编辑按钮事件，然后跳转到编辑弹窗
         * @param {班级Id} classId
         */
        onEditClick(classId){
            this.$root.bus.$emit('class-id',classId);
        },
        /**
         * @function 监听删除班级事件
         * @param {班级Id} classId
         */
        deleteClass(classId){
            this.$confirm('确认删除该课程吗?', '提示', 
                {confirmButtonText: '确定',cancelButtonText: '取消',type: 'warning'})
                .then(async () => {
                    await editClass({claId:classId,claState:4})
                            .catch(err => {
                                xhrErrHandler(err,this.$router,this.$message)
                            })
                    delClasses({claIds:classId})
                        .then((res) => {
                            if(res.data.success){
                                this.$message.success(res.data.message);
                                this.getClassTable({})
                            }else if(res.data.type === 1){
                                this.$message.error(res.data.message);
                            }else{
                                console.log(res.data.message);
                                this.$message.error('发生未知类型错误，请联系管理员')
                            }
                        })
                        .catch(err => {
                            xhrErrHandler(err,this.$router,this.$message)
                        })
                })
                .catch(() => {
                    this.$message.info('已取消删除！')
                })
            
        },
        handleCurrentChange(val) {
            this.getClassTable({
                start:(val-1)*this.pageSize,
                limit:this.pageSize
            })
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
            classTable:state => state.classTable
        })
    },
    mounted(){
        /**获取班级列表 */
        let params ={start:0,limit:this.pageSize};
        this.getClassTable(params)
            .then(res => {
                if(res.success){
                    this.total = res.total;
                }
            })
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

