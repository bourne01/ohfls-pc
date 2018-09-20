<template>
     <div class="ouhai-background">
         <div class="logo">
             <img :src="require('../../assets/ouhai_logo.png')" alt="">
         </div>
         <div class="login">
            <div class="login-tabs">
                <span :class="{active:index===1}" @click="index=1">管理员
                </span>
                <span :class="{active:index===2}" @click="index=2">教师</span>
            </div>
            <div class="login-form">
                <user-input></user-input>
                <password-input></password-input>
                <div class="remember-me">
                    <el-checkbox @change="onCheck">记住密码</el-checkbox>
                </div>
                   <submit-button>登录</submit-button>
            </div>

         </div>
     </div>
    
</template>

<script>
import {xhrErrHandler} from '../../utils/util.js'
import UserInput from '../login-package/username-input'
import PasswordInput from '../login-package/password-input'
import SubmitButton from '../login-package/submit-button'
export default {
    components:{
        UserInput,
        PasswordInput,
        SubmitButton
    },
    data(){
        return{
           index:1,
        }   
    },
    methods:{
        onCheck(val){
            console.log(val);
        }
    },
    created(){
        /**@function  
         * @param {用户Id} Id
         * @param {用户Id} Id
         * @param {用户Id} Id
         * @returns 
        */
    },
    mounted(){
         this.$store.dispatch('user/login',{acc:'admin',psw:'windowsdos88'})
            .then(res => {
                if(res.data.success)
                    this.$router.push('/home/election');
                else{
                    switch(res.data.type){
                        case 1:
                            this.$message('密码错误');
                            break;
                        case 2:
                            this.$message('发生异常，您已登录');
                            break;
                        default:
                            this.$message('发生未知错误，请重试！');
                    }
                }
            })
            .catch(err => {
                console.log(err)
            })
        // this.$store.dispatch('user/getUserInfo')
        //     .then()
        //     .catch(err => {
        //         xhrErrHandler(err,this.$alert)
        //     }) 
    }
    
}
</script>

<style scoped>
    /*这里写样式
    注意：
        1、样式的名称都用英文单词，且都是小写，都统一用类(class)选择器
        2、如果样式名称有两个或以上英文组成，如are you okay,
           则中间用'-'链接，写好的样子：'are-you-okay'
        3、http://codeguide.bootcss.com/
    */
.ouhai-background{
    background-image: url('../../assets/bg.png');
    height: 100%;
    width:  100%;
    Background-repeat:no-repeat;
    background-size:100%;
    
}
.logo{
    margin: 0 auto;
    width: 540px;
    
}
.login{
    width:500px;
    height:464px;
    margin:0 auto;
    border:1px solid #eff0f3;
    border-radius: 5px;
    background-color:rgba(217, 224, 222, 0.8);
    margin-top: 180px;
    }
.login-tabs{
    display:flex;
    justify-content: space-around;
    height:79px;
    background-color:#fff;
    }
.login-tabs span{
    width:86px;
    height:79px;
    line-height:79px;
    text-align: center;
    font-size:24px;
}
.login-form{
     padding:44px 60px 0 60px;
}
.remember-me{
    margin-top:20px;
    margin-bottom:34px;   
    text-align: center;
}
.active{
    position: relative;
    color:#0d714a;
}
.active:after{
    content: " ";
    display: block;
    position: absolute;
    left: 0px;
    bottom: 0px;
    background: #0d714a;
    height:3px;
    width:86px;
        
    }


</style>



