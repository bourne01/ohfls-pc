import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)
export const xhrErrHandler = (err,msgbox) => {
    console.log(err);
    if(err.response){
        let status = err.response.status;
        console.log(status);
        if(status === 401){
            msgbox('您还没有登录','权限错误',/* {center:true} */)
            this.$router.push('/login')
        }else if(status === 404){
            
        }else if(status === 500 || status === 501){
            console.log('网络异常，稍后重试！')
        }
    }
}