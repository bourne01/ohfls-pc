/** 待选课程 */


	/** 待选课Id     */ private Integer selCouId  ;
	/** 选课计划Id   */ private Integer xkpId     ;
	/** 课程类型     */ private Integer couType   ; //系统代码11 2普通(默认) 4自修研习
	/** 父课程Id     */ private Integer parCouId  ; //组合课程时有
	/** 课程编号     */ private String  couNO     ;
	/** 课程名称     */ private String  couName   ;
	/** 场地Id       */ private Integer romId     ;
	/** 课程简介     */ private String  couExplain;
	/** 单期学分(优) */ private Double  credit1   ;
	/** 单期学分(良) */ private Double  credit2   ;
	/** 单期学分(及) */ private Double  credit3   ;
	/** 优秀比率     */ private Double  lvRate1   ;
	/** 良好比率     */ private Double  lvRate2   ;
	/** 及格比率     */ private Double  lvRate3   ;
	/** 最低学分     */ private Double  creditMin ;
	/** 同班限额     */ private Byte    claStuMax ; //同班学生人数限制，默认-1代表不限制
	/** 人数下限     */ private Short   manMin    ; //默认-1代表无限制
	/** 人数上限     */ private Short   manMax    ; //默认-1代表无限制
	/** 男生下限     */ private Short   boyMin    ; //默认-1代表无限制
	/** 男生上限     */ private Short   boyMax    ; //默认-1代表无限制
	/** 女生下限     */ private Short   girlMin   ; //默认-1代表无限制
	/** 女生上限     */ private Short   girlMax   ; //默认-1代表无限制
	/** 当前男生数   */ private Short   boyNum    ;
	/** 当前女生数   */ private Short   girlNum   ;
	/** 学生操作状态 */ private Byte    stuOpState; //1不可见、2可选课(默认)
	/** 委员操作状态 */ private Byte    memOpState; //1不可见(默认)、2可见、3可评价
	/** 教师操作状态 */ private Byte    thrOpState; //控制教师对学生的评价，2不可评(默认)、3可评价 -- 作废保留
	/** 开课状态     */ private Byte    couState  ; //2开启(可选)(默认) 3关闭(不可选) 4确认 5完成
	/** 审核状态     */ private Byte    audit     ; //1未审核(默认) 2已审核
	/** 子课程数量   */ private Integer chiNum    ;

	/** 场地编号   */ private String  romNO  ;
	/** 场地名称   */ private String  romName;
	/** 教室类型   */ private Integer romType; //代码类型46
	/** 教学楼     */ private Integer buiId  ;
	/** 座位数量   */ private Short   seatNum;
	/** 教学楼编号 */ private String  buiNO  ;
	/** 教学楼名称 */ private String  buiName;

	/** 课程教师Id  (组) */ private String  couThrIds   ; //多个用“,”隔开
	/** 教师姓名         */ private String  couThrNm    ;
	/** 社会机构Id  (组) */ private String  socIds      ; //多个用“,”隔开
	/** 社会机构名称     */ private String  socNm       ;
	/** 开课学期Id  (组) */ private String  termIds     ; //多个用“,”隔开
	/** 开课学期名称     */ private String  termNm      ;
	/** 可选年级    (组) */ private String  grades      ; //多个用“,”隔开
	/** 可选年级名称     */ private String  gradeNm     ;
	/** 可选班级Id  (组) */ private String  claIds      ; //多个用“,”隔开
	/** 可选班级名称     */ private String  claNm       ;
	/** 年级最低学分(组) */ private String  graCreMins  ; //多个用“,”隔开
	/** 班级最低学分(组) */ private String  claCreMins  ; //多个用“,”隔开

	/** 排序字段       */ private String[] orderFd  ; //排序字段的名称，倒序前面加“@”，可以多个
	/** 待选课Id(多个) */ private String   selCouIds; //多个Id用“,”隔开

	//==================================================================================================================
	// 查询 -- 基出信息
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：查询 -- 基出信息
	 * 使用者：
	 *
	 * URL：p/selCou!query.action
	 *
	 * 参数
	 * start         可选   记录开始位置
	 * limit         可选   获取记录数量
	 * selCouId      可选   待选课Id
	 * xkpId         可选   选课计划Id
	 * couType       可选   课程类型 系统代码11 2普通(默认) 4自修研习
	 * parCouId      可选   父课程Id -1代表不查子课程
	 * couNO         可选   课程编号
	 * couName       可选   课程名称
	 * romId         可选   场地Id
	 * couExplain    可选   课程简介
	 * credit1       可选   单期学分(优)
	 * credit2       可选   单期学分(良)
	 * credit3       可选   单期学分(及)
	 * lvRate1       可选   优秀比率
	 * lvRate2       可选   良好比率
	 * lvRate3       可选   及格比率
	 * creditMin     可选   最低学分
	 * manMin        可选   人数下限 默认-1代表无限制
	 * manMax        可选   人数上限 默认-1代表无限制
	 * boyMin        可选   男生下限 默认-1代表无限制
	 * boyMax        可选   男生上限 默认-1代表无限制
	 * girlMin       可选   女生下限 默认-1代表无限制
	 * girlMax       可选   女生上限 默认-1代表无限制
	 * boyNum        可选   当前男生数
	 * girlNum       可选   当前女生数
	 * stuOpState    可选   学生操作状态 1不可见、2可选课(默认)
	 * memOpState    可选   委员操作状态 1不可见(默认)、2可见、3可评价
	 * thrOpState    可选   教师操作状态 控制教师对学生的评价，2不可评(默认)、3可评价 -- 作废保留
	 * couState      可选   开课状态 2开启(可选)(默认) 3关闭(不可选) 4确认 5完成
	 * audit         可选   审核状态 1未审核(默认) 2已审核
	 * chiNum        可选   子课程数量
	 * orderFd       可选   排序字段的字段名称，倒序前面加“@”
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"total":"总记录数","datalist":[B_SelCourse]}
	 *
	 */

	//==================================================================================================================
	// 查询2 -- 增加 开课教师、合作机构、开课学期、限制年级、限制班级
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：查询2
	 * 使用者：
	 *
	 * URL：p/selCou!query2.action
	 *
	 * 参数
	 * start         可选   记录开始位置
	 * limit         可选   获取记录数量
	 * selCouIds     可选   待选课Id 多个用“,”隔开
	 * xkpId         可选   选课计划Id
	 * couType       可选   课程类型 2普通(默认) 4自修研习
	 * parCouId      可选   父课程Id -1代表不查子课程
	 * couNO         可选   课程编号
	 * couName       可选   课程名称
	 * romId         可选   场地Id
	 * couExplain    可选   课程简介
	 * credit1       可选   单期学分(优)
	 * credit2       可选   单期学分(良)
	 * credit3       可选   单期学分(及)
	 * term          可选   开课学期 格式：学期Id+","；至少要指定1个学期
	 * creditMin     可选   最低学分
	 * manMin        可选   人数下限
	 * manMax        可选   人数上限
	 * boyMin        可选   男生下限
	 * boyMax        可选   男生上限
	 * girlMin       可选   女生下限
	 * girlMax       可选   女生上限
	 * boyNum        可选   当前男生数
	 * girlNum       可选   当前女生数
	 * stuOpState    可选   学生操作状态 1不可见、2可选课(默认)
	 * memOpState    可选   委员操作状态 1不可见(默认)、2可见、3可评价
	 * thrOpState    可选   教师操作状态 控制教师对学生的评价，2不可评(默认)、3可评价 -- 作废保留
	 * couState      可选   开课状态 2开启(可选)(默认) 3关闭(不可选) 4确认 5完成
	 * audit         可选   审核状态 1未审核(默认) 2已审核
	 * chiNum        可选   子课程数量
	 * couThrIds     可选   课程教师Id  (组)  多个用“,”隔开
	 * couThrNm      可选   教师姓名
	 * socIds        可选   社会机构Id  (组)  多个用“,”隔开
	 * socNm         可选   社会机构名称
	 * termIds       可选   开课学期Id  (组)  多个用“,”隔开
	 * termNm        可选   开课学期名称
	 * grades        可选   可选年级    (组)  多个用“,”隔开
	 * gradeNm       可选   可选年级名称
	 * claIds        可选   可选班级Id  (组)  多个用“,”隔开
	 * claNm         可选   可选班级名称
	 * romNO         可选   场地编号
	 * romName       可选   场地名称
	 * romType       可选   场地类型
	 * buiId         可选   教学楼
	 * buiNO         可选   教学楼编号
	 * buiName       可选   教学楼名称
	 * orderFd       可选   排序字段的字段名称，倒序前面加“@”
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"total":"总记录数","datalist":[V_SelCourse]}
	 *
	 */

	//==================================================================================================================
	// 导出课程信息
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：导出课程信息
	 * 使用者：
	 *
	 * URL：p/selCou!export.action
	 *
	 * 参数：参考query2
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：Excel文件
	 *
	 */

	//==================================================================================================================
	// 我的课程
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：我的课程
	 * 使用者：开课教师、合作机构
	 *
	 * URL：p/selCou!myCou.action
	 *
	 * 参数：参考query2
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"total":"总记录数","datalist":[V_SelCourse]}
	 *
	 */

	//==================================================================================================================
	// 专业委员查询
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：专业委员查询
	 * 使用者：专业委员
	 *
	 * URL：p/selCou!memQue.action
	 *
	 * 参数：参考query2
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"total":"总记录数","datalist":[V_SelCourse]}
	 *
	 */

	//==================================================================================================================
	// 学生查询 -- 所有课程(包括不可选)
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：学生查询 -- 所有课程(包括不可选)
	 * 使用者：学生
	 *
	 * URL：p/selCou!stuQue.action
	 *
	 * 参数：参考query2
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"total":"总记录数","datalist":[V_SelCourse]}
	 *
	 */

	//==================================================================================================================
	// 新增
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：新增
	 * 使用者：+ 全体教师
	 *
	 * URL：p/selCou!newRec.action
	 *
	 * 参数
	 * xkpId         必需   选课计划Id
	 * couType       可选   课程类型 2普通(默认) 4自修研习
	 * parCouId      可选   父课程Id 组合课程时有
	 * couNO         必需   课程编号
	 * couName       必需   课程名称
	 * romId         可选   场地Id
	 * couExplain    可选   课程简介
	 * credit1       可选   单期学分(优)
	 * credit2       可选   单期学分(良)
	 * credit3       可选   单期学分(及)
	 * lvRate1       可选   优秀比率
	 * lvRate2       可选   良好比率
	 * lvRate3       可选   及格比率
	 * creditMin     可选   最低学分
	 * claStuMax     可选   同班限额 同班学生人数限制，默认-1代表不限制
	 * manMin        可选   人数下限 默认-1代表无限制
	 * manMax        可选   人数上限 默认-1代表无限制
	 * boyMin        可选   男生下限 默认-1代表无限制
	 * boyMax        可选   男生上限 默认-1代表无限制
	 * girlMin       可选   女生下限 默认-1代表无限制
	 * girlMax       可选   女生上限 默认-1代表无限制
	 * stuOpState    可选   学生操作状态 1不可见、2可选课(默认)
	 * memOpState    可选   委员操作状态 1不可见(默认)、2可见、3可评价
	 * couThrIds     可选   开课教师 多个用“,”隔开 如果不指定，将默为当前教师自己
	 * socIds        可选   合作机构 多个用“,”隔开
	 * termIds       必需   开课学期 多个用“,”隔开
	 * grades        可选   可选年级 多个用“,”隔开
	 * graCreMins    可选   年级最低学分 多个用“,”隔开 注意：如果存在，要与grades对齐
	 * claIds        可选   可选班级 多个用“,”隔开
	 * claCreMins    可选   班级最低学分 多个用“,”隔开 注意：如果存在，要与claIds对齐
	 * file          可选　 封面文件
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"data":{V_SelCourse}}
	 *
	 */

	//==================================================================================================================
	// 修改
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：修改
	 * 使用者： + 自己(教师/校外机构)的课程
	 *
	 * URL：p/selCou!edit.action
	 *
	 * 参数
	 * selCouId      必需   待选课Id
	 * couType       可选   课程类型 2普通(默认) 4自修研习
	 * couNO         可选   课程编号
	 * couName       可选   课程名称
	 * romId         可选   场地Id
	 * couExplain    可选   课程简介
	 * credit1       可选   单期学分(优)
	 * credit2       可选   单期学分(良)
	 * credit3       可选   单期学分(及)
	 * lvRate1       可选   优秀比率
	 * lvRate2       可选   良好比率
	 * lvRate3       可选   及格比率
	 * creditMin     可选   最低学分
	 * claStuMax     可选   同班学生人数限制，默认-1代表不限制
	 * manMin        可选   人数下限
	 * manMax        可选   人数上限
	 * boyMin        可选   男生下限
	 * boyMax        可选   男生上限
	 * girlMin       可选   女生下限
	 * girlMax       可选   女生上限
	 * stuOpState    可选   学生操作状态 1不可见、2可选课(默认)
	 * memOpState    可选   委员操作状态 1不可见(默认)、2可见、3可评价
	 * couThrIds     可选   开课教师 多个用“,”隔开
	 * socIds        可选   合作机构 多个用“,”隔开
	 * termIds       可选   开课学期 多个用“,”隔开
	 * grades        可选   可选年级 多个用“,”隔开
	 * graCreMins    可选   年级最低学分 多个用“,”隔开 注意：如果存在，要与grades对齐
	 * claIds        可选   可选班级 多个用“,”隔开
	 * claCreMins    可选   班级最低学分 多个用“,”隔开 注意：如果存在，要与claIds对齐
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"message":"成功信息"}
	 *
	 */

    //==================================================================================================================
    // 状态控制
    //------------------------------------------------------------------------------------------------------------------
    /**<pre>
     *   功能：状态控制
     * 使用者：
     *
     * URL：p/selCou!chState.action
     *
     * 参数：
	 * selCouIds    必需   待选课Id 多个用“,”隔开
	 * stuOpState   可选   学生操作状态 1不可见、2可选课(默认)
	 * memOpState   可选   委员操作状态 1不可见(默认)、2可见、3可评价
	 * thrOpState   可选   教师操作状态 控制教师对学生的评价，2不可评(默认)、3可评价 -- 作废保留
	 * couState     可选   开课状态 2开启(可选)(默认) 3关闭(不可选) 4确认 5完成
	 * audit        可选   审核状态 1未审核(默认) 2已审核
     *
     * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
     * 正确返回：{"success":true,"message":"成功信息"}
     *
     */

	//==================================================================================================================
	// 删除
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：删除  注意：不能删除开课状态为“2开启，3关闭”这2种状态的数据
	 * 使用者：
	 *
	 * URL：p/selCou!del.action
	 *
	 * 参数
	 * selCouIds   必需   待选课Id 多个用“,”隔开
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"message":"成功信息"}
	 *
	 */

	//==================================================================================================================
