/** 选课结果 */


	/** 学生选课Id   */ private Integer   stuCouId    ;
	/** 学生Id       */ private Integer   stuId       ;
	/** 学期Id       */ private Integer   termId      ;
	/** 待选课Id     */ private Integer   selCouId    ;
	/** 选课状态     */ private Byte      state       ; //1预选 2选中(默认) 3确认
	/** 选课时间     */ private Timestamp time        ;
	/** 教师评价控制 */ private Byte      evalStateThr; //1不可评(默认) 2可评价
	/** 学生评价控制 */ private Byte      evalStateStu; //1不可评(默认) 2可评价

	/** 学籍号       */ private String    stuNO       ;
	/** 学生姓名     */ private String    stuName     ;
	/** 学生性别     */ private String    stuSex      ; //男(默认)、女
	/** 学分         */ private Double    creditTotal ;
	/** 学生班级Id   */ private Integer   stuClaId    ; //-1代表未分配班级
	/** 学生年级     */ private Integer   stuGrade    ; //就是入学年份，系统代码35
	/** 2正常(默认)  */ private Byte      stuTo       ; //3休学 4退学 5毕业
	/** 学生记录状态 */ private Byte      stuState    ; //2正常(默认) 3关闭 4历史
	/** 学期编号     */ private String    termNO      ;
	/** 学期名称     */ private String    termName    ;
	/** 选课计划Id   */ private Integer   xkpId       ;
	/** 课程类型     */ private Integer   couType     ; //系统代码11 2普通(默认) 4自修研习
	/** 父课程Id     */ private Integer   parCouId    ; //组合课程时有
	/** 课程编号     */ private String    couNO       ;
	/** 课程名称     */ private String    couName     ;
	/** 学生操作状态 */ private Byte      stuOpState  ; //1不可见(默认)、2可见、3可评价
	/** 委员操作状态 */ private Byte      memOpState  ; //1不可见(默认)、2可见、3可评价
	/** 开课状态     */ private Byte      couState    ; //2开启(可选)(默认) 3关闭(不可选) 4确认 5完成
	/** 审核状态     */ private Byte      audit       ; //1未审核(默认) 2已审核
	/** 班级名称     */ private String    claName     ;
	/** 班级年级     */ private Integer   claGrade    ; //就是开班年份，系统代码(35)
	/** 场地Id       */ private Integer   romId       ;
	/** 场地编号     */ private String    romNO       ;
	/** 场地名称     */ private String    romName     ;
	/** 场地类型     */ private Integer   romType     ; //代码类型46
	/** 教学楼       */ private Integer   buiId       ;
	/** 座位数量     */ private Short     seatNum     ;
	/** 教学楼编号   */ private String    buiNO       ;
	/** 教学楼名称   */ private String    buiName     ;

	/** 老师评价     */ private Integer   evalStu     ; //系统代码81 2未评(默认) 4差评 6及格 8良好 10优秀
	/** 老师评语     */ private String    evalStuTxt  ;
	/** 教师评价时间 */ private Timestamp evalStuTime ;
	/** 取得学分     */ private Double    creditCurCou;
	/** 学生评教1    */ private Integer   evalCou1    ; //2未评(默认) 4普通 6满意 8很好 代码82
	/** 学生评教2    */ private Integer   evalCou2    ; //2未评(默认) 4普通 6满意 8很好 代码82
	/** 学生评教3    */ private Integer   evalCou3    ; //2未评(默认) 4普通 6满意 8很好 代码82
	/** 学生总评     */ private Integer   evalCou     ; //2未评(默认) 4普通 6满意 8很好 代码82
	/** 学生评语     */ private String    evalCouTxt  ;
	/** 学生评价时间 */ private Timestamp evalCouTime ;
	/** 互评状态     */ private Byte      evalState   ; //2普通(默认)、3确认

	/** 教师Id(组)   */ private String    couThrIds   ;
	/** 教师姓名(组) */ private String    couThrNms   ;

	/** 排序字段         */ private String[] orderFd  ; //排序字段的名称，倒序前面加“@”，可以多个
	/** 学生选课Id(多个) */ private String   stuCouIds; //多个Id用“,”隔开
	/** 学生Id(多个)     */ private String   stuIds   ; //多个Id用“,”隔开
	/** 导出查询结果     */ private Byte     export   ;

	//==================================================================================================================
	// 查询
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：查询
	 * 使用者：
	 *
	 * URL：p/stuCou!query.action
	 *
	 * 参数
	 * start         可选   记录开始位置
	 * limit         可选   获取记录数量
	 * stuCouId      可选   学生选课Id
	 * stuId         可选   学生Id
	 * termId        可选   学期Id
	 * selCouId      可选   待选课Id
	 * state         可选   选课状态 1预选 2选中(默认) 3确认
	 * evalStateThr  可选   教师评价控制 1不可评(默认) 2可评价
	 * evalStateStu  可选   学生评价控制 1不可评(默认) 2可评价
	 * orderFd       可选   排序字段的字段名称，倒序前面加“@”
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"total":"总记录数","datalist":[StuCourse]}
	 *
	 */


	//==================================================================================================================
	// 查询2 -- 引入：学生、待选课
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：查询2 -- 引入：学生、待选课
	 * 使用者：
	 *
	 * URL：p/stuCou!query2.action
	 *
	 * 参数
	 * start         可选   记录开始位置
	 * limit         可选   获取记录数量
	 * stuCouId      可选   学生选课Id
	 * stuId         可选   学生Id
	 * termId        可选   学期Id
	 * selCouId      可选   待选课Id
	 * state         可选   选课状态 1预选 2选中(默认) 3确认
	 * evalStateThr  可选   教师评价控制 1不可评(默认) 2可评价
	 * evalStateStu  可选   学生评价控制 1不可评(默认) 2可评价
	 * stuNO         可选   学籍号
	 * stuName       可选   学生姓名
	 * stuSex        可选   学生性别 男(默认)、女
	 * creditTotal   可选   学分
	 * stuClaId      可选   学生班级Id -1代表未分配班级
	 * stuGrade      可选   学生年级 就是入学年份，系统代码35
	 * stuTo         可选   2正常(默认) 3休学 4退学 5毕业
	 * stuState      可选   学生记录状态 2正常(默认) 3关闭 4历史
	 * termNO        可选   学期编号
	 * termName      可选   学期名称
	 * xkpId         可选   选课计划Id
	 * couType       可选   课程类型 系统代码11 2普通(默认) 4自修研习
	 * parCouId      可选   父课程Id -1代表不查子课程
	 * couNO         可选   课程编号
	 * couName       可选   课程名称
	 * stuOpState    可选   学生操作状态 1不可见(默认)、2可见、3可评价
	 * memOpState    可选   委员操作状态 1不可见(默认)、2可见、3可评价
	 * couState      可选   开课状态 2开启(可选)(默认) 3关闭(不可选) 4确认 5完成
	 * audit         可选   审核状态 1未审核(默认) 2已审核
	 * claName       可选   班级名称
	 * claGrade      可选   班级年级 就是开班年份，系统代码(35)
	 * romId         可选   场地Id
	 * romNO         可选   场地编号
	 * romName       可选   场地名称
	 * romType       可选   场地类型
	 * buiId         可选   教学楼
	 * buiNO         可选   教学楼编号
	 * buiName       可选   教学楼名称
	 * orderFd       可选   排序字段的字段名称，倒序前面加“@”
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"total":"总记录数","datalist":[V_StuCourse]}
	 *
	 */


	//==================================================================================================================
	// 查询3 -- 引入：学生、待选课、评价
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：查询3 -- 引入：学生、待选课、评价
	 * 使用者：
	 *
	 * URL：p/stuCou!query3.action
	 *
	 * 参数
	 * start         可选   记录开始位置
	 * limit         可选   获取记录数量
	 * stuCouId      可选   学生选课Id
	 * stuId         可选   学生Id
	 * termId        可选   学期Id
	 * selCouId      可选   待选课Id
	 * state         可选   选课状态 1预选 2选中(默认) 3确认
	 * evalStateThr  可选   教师评价控制 1不可评(默认) 2可评价
	 * evalStateStu  可选   学生评价控制 1不可评(默认) 2可评价
	 * stuNO         可选   学籍号
	 * stuName       可选   学生姓名
	 * stuSex        可选   学生性别 男(默认)、女
	 * creditTotal   可选   学分
	 * stuClaId      可选   学生班级Id -1代表未分配班级
	 * stuGrade      可选   学生年级 就是入学年份，系统代码35
	 * stuTo         可选   2正常(默认) 3休学 4退学 5毕业
	 * stuState      可选   学生记录状态 2正常(默认) 3关闭 4历史
	 * termNO        可选   学期编号
	 * termName      可选   学期名称
	 * xkpId         可选   选课计划Id
	 * couType       可选   课程类型 系统代码11 2普通(默认) 4自修研习
	 * parCouId      可选   父课程Id -1代表不查子课程
 	 * couNO         可选   课程编号
	 * couName       可选   课程名称
	 * stuOpState    可选   学生操作状态 1不可见(默认)、2可见、3可评价
	 * memOpState    可选   委员操作状态 1不可见(默认)、2可见、3可评价
	 * couState      可选   开课状态 2开启(可选)(默认) 3关闭(不可选) 4确认 5完成
	 * audit         可选   审核状态 1未审核(默认) 2已审核
	 * claName       可选   班级名称
	 * claGrade      可选   班级年级 就是开班年份，系统代码(35)
	 * evalStu       可选   老师评价 系统代码81 2未评(默认) 4差评 6及格 8良好 10优秀
	 * evalStuTxt    可选   老师评语
	 * creditCurCou  可选   取得学分
	 * evalCou1      可选   学生评教1 2未评(默认) 4普通 6满意 8很好 代码82
	 * evalCou2      可选   学生评教2 2未评(默认) 4普通 6满意 8很好 代码82
	 * evalCou3      可选   学生评教3 2未评(默认) 4普通 6满意 8很好 代码82
	 * evalCou       可选   学生总评 2未评(默认) 4普通 6满意 8很好 代码82
	 * evalCouTxt    可选   学生评语
	 * evalState     可选   互评状态 2普通(默认)、3确认
	 * romId         可选   场地Id
	 * romNO         可选   场地编号
	 * romName       可选   场地名称
	 * romType       可选   场地类型 代码类型46
	 * buiId         可选   教学楼
	 * buiNO         可选   教学楼编号
	 * buiName       可选   教学楼名称
	 * orderFd       可选   排序字段的字段名称，倒序前面加“@”
	 * export        可选   1=导出查询结果
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"total":"总记录数","datalist":[V_StuCourseEval]}
	 *
	 */


	//==================================================================================================================
	// 开课教师、合作机构查询
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：开课教师、合作机构查询
	 * 使用者：开课教师、合作机构
	 *
	 * URL：p/stuCou!myCouStu.action
	 *
	 * 参数：参考query3
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"total":"总记录数","datalist":[V_StuCourseEval]}
	 *
	 */


	//==================================================================================================================
	// 专业委员查询
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：专业委员查询
	 * 使用者：专业委员
	 *
	 * URL：p/stuCou!memQue.action
	 *
	 * 参数：参考query3
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"total":"总记录数","datalist":[V_StuCourseEval]}
	 *
	 */


	//==================================================================================================================
	// 学生查询
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：学生查询
	 * 使用者：学生
	 *
	 * URL：p/stuCou!stuQue.action
	 *
	 * 参数：参考query3
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"total":"总记录数","datalist":[V_StuCourseEval]}
	 *
	 */


	//==================================================================================================================
	// 新增
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：新增
	 * 使用者：
	 *
	 * URL：p/stuCou!newRec.action
	 *
	 * 参数
	 * selCouId  必需   待选课Id
	 * stuIds    必需   学生Id 多个用“,”隔开
	 * termId    可选   学期Id；如果不指定将默认取用待选课的所有开课学期
	 * time      可选   选课时间 默认取当前时间
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"message":"成功信息"}
	 *
	 */


	//==================================================================================================================
	// 状态控制 注：状态值的修改，只能以待选课为最小单位
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：状态控制
	 * 使用者：
	 *
	 * URL：p/stuCou!chState.action
	 *
	 * 参数
	 * stuCouIds     必需   学生选课Id 多个用“,”隔开
	 * state         可选   选课状态 1预选 2选中(默认) 3确认
	 * evalStateThr  可选   教师评价控制 1不可评(默认) 2可评价
	 * evalStateStu  可选   学生评价控制 1不可评(默认) 2可评价
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"message":"成功信息"}
	 *
	 */


	//==================================================================================================================
	// 删除
	//------------------------------------------------------------------------------------------------------------------
	/**<pre>
	 *   功能：删除  注意：不能删除“3确认”状态的数据
	 * 使用者：
	 *
	 * URL：p/stuCou!del.action
	 *
	 * 参数
	 * stuCouIds   必需   学生选课Id 多个用“,”隔开
	 *
	 * 出错返回：{"success":false,"type":1或2,"message":"错误信息"} 1已知错误、2未知错误
	 * 正确返回：{"success":true,"message":"成功信息"}
	 *
	 * 注：会关联删除子课程的选课结果。子课程选课结果被动删除时，不受状态约束
	 *
	 */


	//==================================================================================================================
