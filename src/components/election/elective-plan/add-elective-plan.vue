<template>
    <article class="plan-wrapper"> 
        <section>
            <header><span>课程消息</span><img src="" alt=""></header>                  <!--新增课程页面-->
            <div class="flex">
                <div class="plan-name">
                    <label for=""><span>*</span>计划名称</label>
                    <el-input placeholder="输入名称" v-model="plan.name"></el-input>
                </div>
                <div class="plan-id">
                    <label for=""><span>*</span>计划编号</label>
                    <el-input placeholder="输入编号" v-model="plan.id"></el-input>
                </div>
            </div>
            <div>
                <div class="teaching-site">
                    <label for="">教学场地</label>
                    <el-select  v-model="plan.site"></el-select>
                </div>                        
            </div>  
            <div class="plan-intro">
                <label for="">计划说明</label>
                <el-input type="textarea" v-model="plan.memo"></el-input>
            </div>
            <div>
                <label for="">报名时间<span class="end-time"></span></label>                
                <el-date-picker
                v-model="plan.time"
                type="datetimerange"
                align="right"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :default-time="['12:00:00', '08:00:00']">
                </el-date-picker>
            </div>   
            <div class="flex ratio">
                <div>
                    <label for="">特优比率(%)</label>
                    <el-input v-model="plan.ultimateRatio"></el-input>
                </div>
                <div>
                    <label for="">优秀比率(%)</label>
                    <el-input v-model="plan.excellentRatio"></el-input>
                </div>
                <div>
                    <label for="">良好比率(%)</label>
                    <el-input v-model="plan.goodRatio"></el-input>
                </div>
            </div>  
            <div class="flex credit">
                <div>
                    <label for="">特优学分</label>
                    <el-input  v-model="plan.ultimateCredit"></el-input>
                </div>
                <div>
                    <label for="">优秀学分</label>
                    <el-input v-model="plan.excellentCredit"></el-input>
                </div>
                <div>
                    <label for="">良好学分</label>
                    <el-input v-model="plan.goodCredit"></el-input>
                </div>
            </div>
            <div>
                <span class="open">开放新增/编辑</span>
                <el-switch
                    v-model="plan.isOpen"
                    active-color="#0d714a"
                    inactive-color="#ff4949">
                </el-switch>
            </div>
            <div class="save">
                <el-button @click="submit">保存并返回</el-button>
            </div>      
        </section>          
    </article>
</template>



<script>
  export default {
    components:{
    },
    data() {
        return {
            plan:{},
        };
    },
    methods: {
        /**@function 提交选课计划*/
        submit(){
            console.log(this.plan);
            this.$router.push('/home/election')
        },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      }
    }
  }
</script>

<style>    
    .plan-wrapper .el-input__inner{
        background-color:#f6f9fb;
    }
    .plan-wrapper .el-textarea__inner{
        background-color:#f6f9fb;
        min-height:88px!important;
    }
</style>


<style scoped>    
    .plan-wrapper section{
        padding-top:50px;
        background-color:#fff;
        text-align:left;
        padding-bottom:10px;
    }
    section.plan-setting{
        margin-top:10px;
    }
    .plan-wrapper section>header{
        padding-left:70px;
        margin-bottom:40px;
        font-size: 24px;
        font-family: 'MicrosoftYaHei';
        color:#1a1a1f;
    }
    .plan-wrapper section>div{
        padding-left:70px;
        margin-bottom:40px;
    }   
    
    .flex{
        display:flex;
    }
    label{
        display:block;
        margin-bottom:10px;
        font-size:14px;
    }
    label>span{
        color:red;
        margin-right:5px;
    }
   .plan-name .el-input,
   .teaching-site .el-select{
       width:392px;
       margin-right:38px;
   }
   .plan-id .el-input,
   .plan-type .el-select{
       width:320px;
   }
   .plan-intro .el-textarea{
       width:752px;
       /* min-height:88px!important; */
   }  
    .limit,.ratio,.credit{
        justify-content:space-between;   
        width:752px;     
    }
    .limit .el-input{
        width:150px;
    }
    .ration .el-input,
    .credit .el-input{
        width:200px;
    }
    .lowest-credit .el-input{
        width:200px;
    } 
    .open{
        font-size:14px;
        color:#2b2b32;
        margin-right:150px;   
    }
    .save .el-button{               /*保存并返回按钮的样式*/
        width: 172px;
        height: 44px;
        font-size: 16px;
        line-height:44px;
        background-color: #0d714a;
        border: none;
        color:#fff;
        padding:0;
    }
</style>

















