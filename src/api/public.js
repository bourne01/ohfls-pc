/* import axios from 'axios'
axios.defaults.timeout = 5000
axios.defaults.headers.post['Content-Type'] = 'application/x-www=form-urlencoded'
export default {
  fetchGet (url, params = {}) {
    return new Promise((resolve, reject) => {
      axios.get(url, {params}).then(res => {
        resolve(res.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  fetchPost (url, params = {}) {
    return new Promise((resolve, reject) => {
      axios.post(url, params).then(res => {
        resolve(res.data)
      }).catch(error => {
        reject(error)
      })
    })
  }
} */
import http from 'axios'

const proxyURL = '/p'
const baseURL = proxyURL+'/p'
/********************************************************************************************
 * 下拉列表
 ********************************************************************************************/
/**
 * @function 
 */
export const selector = (params) => {
  return http(`${baseURL}/baseWebDat`,{params})

}
/********************************************************************************************
 * 学期信息
 ********************************************************************************************/
/**
 * @function 获取学期信息
 */
export const getTerms = (params) => {
  return http(`${baseURL}/term!query.action`,{params});
}
/**
 * @function 新增学期
 */
export const addTerm = (params) => {
  return http(`${baseURL}/term!newRec.action`,{params});
}
/**
 * @function 编辑学期
 */
export const editTerm = (params) => {
  return http(`${baseURL}/term!edit.action`,{params});
}
/**
 * @function 删除学期
 */
export const delTerm = (params) => {
  return http(`${baseURL}/term!del.action`,{params});
}
/********************************************************************************************
 * 系统代码
 ********************************************************************************************/
/**
 * @function 获取系统代码
 */
export const getSysCodes = (params) => {
  return http(`${baseURL}/code!query.action`,{params});
}
/**
 * @function 新增系统代码
 */
export const addSysCode = (params) => {
  return http(`${baseURL}/code!newRec.action`,{params});
}
/**
 * @function 编辑系统代码
 */
export const editSysCode = (params) => {
  return http(`${baseURL}/code!edit.action`,{params});
}
/**
 * @function 删除系统代码
 */
export const delSysCode = (params) => {
  return http(`${baseURL}/code!del.action`,{params});
}
/********************************************************************************************
 * 系统设置
 ********************************************************************************************/
/**
 * @function 获取系统设置
 */
export const getSysSettings = (params) => {
  return http(`${baseURL}/sysSet!query.action`,{params});
}
/**
 * @function 新增系统设置
 */
export const addSysSetting = (params) => {
  return http(`${baseURL}/sysSet!newRec.action`,{params});
}
/**
 * @function 编辑系统设置
 */
export const editSysSettings = (params) => {
  return http(`${baseURL}/sysSet!edit.action`,{params});
}
/**
 * @function 删除系统设置
 */
export const delSysSettings = (params) => {
  return http(`${baseURL}/sysSet!del.action`,{params});
}

