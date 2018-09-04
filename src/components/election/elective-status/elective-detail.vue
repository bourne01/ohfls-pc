<template>
    <el-dialog
        :visible.sync="isShow"
        width="60%"
        :modal-append-to-body="false"    
        @close="onClose"        
        class="my-dialog">
        <header slot="title">
            <span>选课详情</span>
        </header>
        <section>
            <el-table
                ref="multipleTable"
                :data="electiveDetail"
                tooltip-effect="dark"
                style="width: 100%"
                @selection-change="handleSelectionChange"
                border>
                <el-table-column
                    prop="stuName"
                    label="姓名"
                    width="100">
                </el-table-column>
                <el-table-column
                    prop="stuNO"
                    label="学号"
                    width="180">
                </el-table-column>		
                
                <el-table-column
                    prop="couName"
                    label="课程名称">         <!--课程名称列-->
                </el-table-column>		
                <el-table-column	
                    label="选课状态">  
                     <template slot-scope="scope">
                        <span :style="{color:scope.row.canSel===1?'#0d714a':'#ff7a7b'}">
                            {{scope.row.canSel|stateConverter('Select')}}
                        </span>                        
                    </template>
                </el-table-column>
                <el-table-column
                    label="时间">  
                    <template slot-scope="scope">
                        <span :style="{color:scope.row.stTime===1?'#0d714a':'#ff7a7b'}">
                            {{scope.row.stTime|stateConverter('Time')}}
                        </span>                        
                    </template>
                </el-table-column>
                <el-table-column
                    label="性别">  
                    <template slot-scope="scope">
                        <span :style="{color:scope.row.stSex===1?'#0d714a':'#ff7a7b'}">
                            {{scope.row.stSex|stateConverter('Gender')}}
                        </span>                        
                    </template>
                </el-table-column>
                <el-table-column
                    label="学分">                    
                    <template slot-scope="scope">
                        <span :style="{color:scope.row.stCredit===1?'#0d714a':'#ff7a7b'}">
                           {{scope.row.stCredit|stateConverter('Credit')}}
                        </span>
                    </template>
                </el-table-column>
                <el-table-column
                    label="年级">
                    <template slot-scope="scope">
                        <span :style="{color:scope.row.stGrade===1?'#0d714a':'#ff7a7b'}">
                           {{scope.row.stGrade|stateConverter('Grade')}}
                        </span>
                    </template>
                </el-table-column>
                <el-table-column
                    label="班级">
                     <template slot-scope="scope">
                        <span :style="{color:scope.row.stClass===1?'#0d714a':'#ff7a7b'}">
                           {{scope.row.stClass|stateConverter('Class')}}
                        </span>
                    </template>
                </el-table-column>
                <el-table-column
                    label="人数上限"
                    prop="manMax">                    
                </el-table-column>
                <el-table-column
                    label="当期人数">
                    <template slot-scope="scope">
                        {{scope.row.boyNum+scope.row.girlNum}}
                    </template>
                </el-table-column>
            </el-table>
        </section>
    </el-dialog>
</template>

<script>
export default {
    props:['is-pop','elective-detail'],
    data(){
        return{
            title:'',           
       
        }
    },
    computed:{
        isShow:{
            /**@function 根据来自父组件属性值，打开哪个标题名称的弹窗 */
            get:function(){                
                return this.isPop;
            },
            set:function(val){
            }
        }
    },
    filters:{
		/**
		*@function 根据状态值转化为状态名
		*@param {输入值} val
		*@param {状态类型} type
		*@return 返回状态名称
		*/
		stateConverter(val,type){
			let selCouStates = ['不可选','可选'];
			let timeStates = ['未到','正常','已过'];
			let genderStates = ['不符','正常'];
			let creditStates = ['不符','正常'];
			let gradeStates = ['不符','正常','学分不足'];
			let classStates = ['不符','正常','学分不足'];
			let value = val;
			switch(type){
				case 'Select':
					value = selCouStates[val];
					break;
				case 'Time':
					value = timeStates[val];
					break;
				case 'Gender':
					value = genderStates[val];
					break;
				case 'Credit':
					value = creditStates[val];
					break;
				case 'Grade':
					value = gradeStates[val];
					break;
				case 'Class':
					value = classStates[val];
					break;
				default:
					value = '未知'
			}
			return value;
        }
    },
    methods:{
        /**
         * 监听对话框关闭事件，然后向父组件传递关闭事件
         */
        onClose(){
            this.$emit('dialog-close')
        },
        handleSelectionChange(){},
        filterNode(){},
    },
    mounted(){
        console.log(this.electiveDetail)
    }
}
</script>

<style>
    .my-dialog .el-dialog__close{
        font-size:20px;
        color:#fff;
    }
    .my-dialog .el-dialog__header{
        padding:15px 20px;
        border-bottom:1px solid #f1f1f1;
        text-align: left;
        background-color:#0d714a;
    }
    .my-dialog .el-dialog__headerbtn{
        top:15px;
    }
    .my-dialog .el-dialog__body{
        padding:0;
    }    
</style>

<style scoped>
    .my-dialog{
        font-size:18px;        
    }
    header>span{
        color:#fff;
    }
    .left{
        width:289px;
        padding-top:20px;
        padding-right:20px;
        border-right:1px solid #f1f1f1;
    }
    .right{
        padding:20px 0 0 20px;
        width:376px;
    }
    .right-header{
        height:40px;
        line-height:40px;
        display:flex;
        justify-content:space-between;
    }
    .right li:hover{
        background-color:#f6f9fb;
    }
    .right .el-icon-circle-close{
        font-size:18px;
        color:#bbbbc6;
    }
    .right .el-icon-circle-close:hover{
        color:#ff7a7b;
    }
    section{
        padding:20px;
        display:flex;
    }
    .el-tree,
    .right ul{
        height:400px;
        overflow:auto;
    }
    .right li{
        padding:10px;
        display:flex;
        justify-content: space-between;
    }
    footer{
        height: 65px;
        line-height: 65px;
        display: flex;
        justify-content: flex-end;
        padding:0 20px;
        border-top:1px solid #f1f1f1;
        background-color:#eff2f4;
    }
    footer button{
        height:32px;
        line-height:32px;
        width:126px;
        font-size:14px;
        background-color:#2185ff;
        outline:none;
        border:none;
        color:#fff;
        align-self: center;
        border-radius:3px;
    }
</style>


