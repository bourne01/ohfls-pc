/** 下拉列表 */


    //==================================================================================================================
    // 接口总说明
    //------------------------------------------------------------------------------------------------------------------
    /**<pre>
     *
     * URL：p/baseWebDat
     * 
     * 参数：f  调用的方法名，必需正确提供
     * f = uxCode   Code相关
     * f = uxTerm   学期
     * f = uxCla    班级
     * f = uxBui    教学楼
     * f = uxRom    场地
     *
     * 出错返回：{"success":false,"message":"错误信息"}
     *
     */

    //==================================================================================================================
    // 代码信息
    //------------------------------------------------------------------------------------------------------------------
    /**<pre>
     * 功能：Code相关下拉选择信息
     *
     * 参数：
     * codeType   必需   代码类型
     * infType    可选   1=返回[no,name]，否则返回[id,name]
     * addType    可选   1=附加[0,无限制]
     * state      可选   状态：2正常(默认) 3关闭 4历史
     * simple     可选   0 = 返回：{"dataList":[{'id':'?','name':'?'},...]}
     *                   默认返回：[[id,name],...]
     */

    //==================================================================================================================
    // 学期
    //------------------------------------------------------------------------------------------------------------------
    /**<pre>
     * 功能：学期
     *
     * 参数：
     * addType   可选   附加类型：1=附加[0,无限制]
     * state     可选   状态：2正常、3关闭、4历史
     * simple    可选   0 = 返回：{"datalist":[{'id':'?','name':'?'},...]}
     *                  默认返回：[[id,name],...]
     */

    //==================================================================================================================
    // 班级
    //------------------------------------------------------------------------------------------------------------------
    /**<pre>
     * 功能：班级(按名称排序)
     *
     * 参数：
     * addType   可选   附加：1=[0,无限制]
     * grades    可选   年级 多个用“,”隔开
     * rtnType   可选   返回名称类型：1=班级名称(默认)  2=年级名称+班级名称
     * state     可选   状态：2正常、3关闭、4历史
     * simple    可选   0 = 返回：{"datalist":[{'id':'?','name':'?'},...]}
     *                  默认返回：[[id,name],...]
     */

    //==================================================================================================================
    // 教学楼
    //------------------------------------------------------------------------------------------------------------------
    /**<pre>
     * 功能：教学楼
     *
     * 参数：
     * addType   可选   附加类型：1=附加[0,无限制]
     * state     可选   状态：2正常、3关闭、4历史
     * simple    可选   0 = 返回：{"datalist":[{'id':'?','name':'?'},...]}
     *                  默认返回：[[id,name],...]
     */

    //==================================================================================================================
    // 场地
    //------------------------------------------------------------------------------------------------------------------
    /**<pre>
     * 功能：场地
     *
     * 参数：
     * addType   可选   附加类型：1=附加[0,无限制]
     * buiId     可选   教学楼Id
     * state     可选   状态：2正常、3关闭、4历史
     * simple    可选   0 = 返回：{"datalist":[{'id':'?','name':'?'},...]}
     *                  默认返回：[[id,name],...]
     */
    
    //==================================================================================================================
