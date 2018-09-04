import {getTeachers} from  '../../api/teacher' 
import * as type from './mutations-type'


const state = {
     teacherTable:[] //获取教师列表
}

const getters ={

}

const mutations ={
    [type.GET_TEACHERES](state,params){
        state.teacherTable = params;
    }
}

const actions = {
    //获取教师管理信息
    getTeacherTable({commit},params){
        return new Promise((resolve,reject) => {
            getTeachers(params.url,params.params)
                .then(res => {
                    commit('GET_TEACHERES',res.data.dataList);
                    resolve(res.data);
                })
                .catch(err => {
                    console.log(err)
                    reject(err)
                })
        })
    }
}

export default {
    namespaced:true,
    state,     
    getters,     
    mutations,     
    actions 
}