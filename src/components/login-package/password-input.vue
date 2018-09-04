<template>
  <div class="password-input">
      <input type="password" v-model="password" name="password" class="password" @change="onBlur" placeholder="请输入密码">
      <img :src="require('../../assets/Sign in_password.png')" alt="" class="lock">
  </div>
</template>

<script>
export default {
    data(){
        return{
            password:''
        }
    },
    methods:{
        onBlur(){
            let account = this.$store.state.user.user.account;
            this.$store.commit('user/SET_USER',{account,password:this.password});
        }
    },
    
    created(){
        
    }
}
</script>

<style scoped>   
    .password{
        border-radius: 5px;
        height: 58px;
        line-height: 58px;
        font-size:18px;
        border:none;
        width:310px;
        padding-left:70px;
        outline:none;
    }
    .password-input{
        position: relative;
    }
    .lock{
        width:31px;
        position: absolute;
        left:23px;
        top:13px
    }
</style>


