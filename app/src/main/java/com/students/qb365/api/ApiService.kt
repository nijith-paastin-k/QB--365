package com.students.qb365.api

import com.students.qb365.ui.auth.model.*
import com.students.qb365.ui.auth.model.Chapters
import com.students.qb365.ui.auth.model.Subjects
import com.students.qb365.ui.dashboard.model.Profile
import com.students.qb365.ui.dashboard.model.Search
import com.students.qb365.ui.dashboard.model.TestSubjects
import com.students.qb365.ui.navigationMenu.model.*
import com.students.qb365.ui.report.model.*
import com.students.qb365.ui.subject_mode.model.*
import com.students.qb365.ui.subject_mode.practiceQuestion.model.PracticeQuestion
import com.students.qb365.ui.subject_mode.writeTest.model.FinishTest
import com.students.qb365.ui.subject_mode.writeTest.model.Overview
import com.students.qb365.ui.subject_mode.writeTest.model.WriteTest
import retrofit2.Call
import retrofit2.http.*

/**
 * All APIs are here
 */
interface ApiService {

    /**
     * 1. Register
     */
    @POST("register/add_user")
    suspend fun register(@Body data: HashMap<String, Any>): Register


    /**
     * 2. Login
     */
    @FormUrlEncoded
    @POST("register/login")
    suspend fun login(
        /*   @Header("Authorization") auth : String,*/
        @Field("email") email: String,
        @Field("password") password: String,
    ): Login


    /**
     * 3. Forgot password
     */
    @FormUrlEncoded
    @POST("register/forgot_password")
    suspend fun forgotPassword(
        @Field("phone") phone: String,
    ): ForgotPassword


    /**
     * 4. Reset Password
     */
    @FormUrlEncoded
    @POST("register/forgot_password")
    suspend fun resetPassword(
        @Field("phone") phone: String,
        @Field("password") password: String,
        @Field("rpassword") rpassword: String,
    ): ResetPassword


    /**
     * 5. Get All State of India
     */
    @GET("register/get_state")
    suspend fun getState(): State


    /**
     * 6. Get City as per State
     */
    @FormUrlEncoded
    @POST("register/get_city")
    suspend fun getCity(
        @Field("state_id") state_id: String,
    ): City


    /**
     * 7. Get All Boards
     */
    @GET("register/get_board")
    suspend fun getBoards(@Header("Authorization") auth: String): Board


    /**
     * 8. Get Classes as per Boards
     */
    @FormUrlEncoded
    @POST("register/get_classofboard")
    suspend fun getClass(
        @Field("board") board_id: String,
    ): Classes


    /**
     * 9. Get Purchase Type
     */
    @GET("register/get_type")
    suspend fun getPurchaseType(): PurchaseType


    /**
     * 10. Get Subjects
     */
    @FormUrlEncoded
    @POST("register/get_subjects")
    suspend fun getSubjects(
        @Field("board") board_id: String,
        @Field("pack_id") pack_id: String,
    ): Subjects


    /**
     * 11. Get Chapters
     */
    @FormUrlEncoded
    @POST("register/get_chapters")
    suspend fun getChapters(
        @Field("board") board_id: String,
        @Field("qbs_sub_id") qbs_sub_id: String,
    ): Chapters


    /**
     * 12. Check Phone Number
     */
    @FormUrlEncoded
    @POST("register/checkphone")
    suspend fun checkPhone(
        @Field("phone") phone: String,
    ): ForgotPassword


    /**
     * 13. Get Dashboard data
     */
    @POST("site")
    suspend fun dashboard(@Header("Authorization") token: String): Dashboard


    /**
     * 14. Get chapter of specific subject
     */
    @FormUrlEncoded
    @POST("site/practice_chapters")
    suspend fun getSubjectChapter(
        @Field("qbs_sub_id") qbs_sub_id: String,
        @Field("board") board: String,
    ): SubjectChapter


    /**
     * 15. Get chapter of specific subject
     */
    @FormUrlEncoded
    @POST("site/practice_question_type")
    suspend fun getQuestionType(
        @Field("qbs_sub_id") qbs_sub_id: String,
        @Field("qbs_chapter_id") qbs_chapter_id: String,
        @Field("board") board: String,
    ): QuestionType


    /**
     * 16. Get Write Test Type (Subject based, Chapter based, Today)
     */
    @FormUrlEncoded
    @POST("site/test_subjects_single")
    suspend fun getWriteTestType(
        @Field("qbs_sub_id") qbs_sub_id: String,
        @Field("board") board: String,
    ): WriteTestType


    /**
     * 17. Get Subject based test(Write test)
     */
    @FormUrlEncoded
    @POST("site/exams_list_subject")
    suspend fun getSubjectBasedTest(
        @Field("qbs_sub_id") qbs_sub_id: String,
        @Field("board") board: String,
    ): SubjectBasedTest


    /**
     * 18. Get Chapter List for based test(Write test)
     */
    @FormUrlEncoded
    @POST("site/chapters")
    suspend fun getChapterListTest(
        @Field("qbs_sub_id") qbs_sub_id: String,
        @Field("board") board: String,
    ): ChapterListTest


    /**
     * 19. Get Chapter based test list (Write test)
     */
    @FormUrlEncoded
    @POST("site/exams_list_chapter")
    suspend fun getChapterBasedTestList(
        @Field("test_chapter_id") test_chapter_id: String,
        @Field("board") board: String,
    ): ChapterBasesTest


    /**
     * 20. Get Exam instruction
     */
    @FormUrlEncoded
    @POST("site/examinstruction")
    suspend fun getExamInstruction(
        @Field("test_id") test_id: String,
        @Field("board") board: String,
    ): ExamInstruction


    /**
     * 21. Get Profile
     */
    @FormUrlEncoded
    @POST("profile")
    suspend fun getProfile(
        @Field("board") board: String,
    ): Profile


    /**
     * 22. Edit Profile
     */
    @FormUrlEncoded
    @POST("profile/editprofile")
    suspend fun editProfile(
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("name") name: String,
        @Field("image") image: String,
        @Field("board") board: String,
        @Field("school") school: String,
        @Field("address") address: String,
    ): BaseResponse


    /**
     * 23. Change Password
     */
    @FormUrlEncoded
    @POST("profile/change_password")
    suspend fun changePassword(
        @Field("old_password") old_password: String,
        @Field("new_password") phone: String,
        @Field("confirm_password") confirm_password: String,
        @Field("board") board: String,
    ): BaseResponse


    /**
     * 24. Contact Us
     */
    @FormUrlEncoded
    @POST("contact")
    suspend fun contact(
        @Field("message") message: String,
    ): BaseResponse2


    /**
     * 25. Validity
     */
    @POST("site/validity")
    suspend fun getValidity(): Validity


    /**
     * 26. Write Test (Question and answer)
     */
    @FormUrlEncoded
    @POST("site/write_test")
    suspend fun writeTest(
        @Field("board") board: String,
        @Field("test_id") test_id: String,
    ): WriteTest


    /**
     * 27. Finish Test Results
     */
    @FormUrlEncoded
    @POST("site/finish_test")
    suspend fun finishTest(
        @FieldMap data: HashMap<String, Any>
    ): FinishTest


    /**
     * 28. Answer Key of Test
     */
    @FormUrlEncoded
    @POST("site/answer_key")
    suspend fun answerKey(
        @Field("board") board: String,
        @Field("qbs_exam_id") qbs_exam_id: String,
        @Field("id") id: String,
    ): WriteTest


    /**
     * 29. Overview of Test
     */
    @FormUrlEncoded
    @POST("site/overview")
    suspend fun overview(
        @Field("board") board: String,
        @Field("test_id") test_id: String,
    ): Overview


    /**
     * 30. Test Subjects
     */
    @FormUrlEncoded
    @POST("site/test_subjects")
    suspend fun testSubjects(
        @Field("board") board: String,
    ): TestSubjects


    /**
     * 31. Practice Questions
     */
    @FormUrlEncoded
    @POST("site/practice_questions")
    suspend fun practiceQuestions(
        @Field("qbs_subject_id") qbs_subject_id: String,
        @Field("qbs_chapter_id") qbs_chapter_id: String,
        @Field("qbs_qst_type") qbs_qst_type: String,
        @Field("qbs_creative") qbs_creative: String,
        @Field("board") board: String,
    ): PracticeQuestion


    /**
     * 32. Test Report
     */
    @FormUrlEncoded
    @POST("site/report")
    suspend fun testReport(
        @Field("qbs_sub_id") qbs_subject_id: String,
        @Field("board") board: String,
    ): TestReport


    /**
     * 33. For you
     */
    @FormUrlEncoded
    @POST("for_you")
    suspend fun forYou(
        @Field("board") board: String,
    ): ForYou


    /**
     * 34. Invite & Earn
     */
    @POST("invite_earn")
    suspend fun inviteEarn(): InviteEarn


    /**
     * 35. Get Subject Based Test Reports (Five Test Marks)
     */
    @FormUrlEncoded
    @POST("site/report_exams_list_subject")
    suspend fun subjectBasedReport(
        @Field("test_sub_id") qbs_subject_id: String,
        @Field("board") board: String,
    ): SubjectBasedReport


    /**
     * 36. Get Attempts (Subject based report)
     */
    @FormUrlEncoded
    @POST("site/attempts")
    suspend fun getAttempts(
        @Field("test_id") qbs_subject_id: String,
        @Field("board") board: String,
    ): Attempts


    /**
     * 37. Get Subject Based Test Reports (Five Test Marks)
     */
    @FormUrlEncoded
    @POST("site/attempts_single")
    suspend fun getAttemptSingle(
        @Field("test_id") qbs_subject_id: String,
        @Field("board") board: String,
        @Field("attempt_id") attempt_id: String,
    ): AttemptsSingle


    /**
     * 38. Tests of Selected Chapter
     */
    @FormUrlEncoded
    @POST("site/report_exams_list_chapter")
    suspend fun reportExamListChapter(
        @Field("test_chapter_id") qbs_subject_id: String,
        @Field("board") board: String,
    ): ReportExamListChapter


    /**
     * 39. Search
     */
    @FormUrlEncoded
    @POST("site/search")
    suspend fun search(
        @Field("search") search: String,
    ): Search


    /**
     * 40. OverAll report
     */
    @FormUrlEncoded
    @POST("site/overall_reports")
    suspend fun overAllReport(
        @Field("board") board: String,
    ): OverAllReport


    /**
     * 41. Test Wise report
     */
    @FormUrlEncoded
    @POST("site/testwise_report")
    suspend fun testWiseReport(
        @Field("board") board: String,
    ): TestWiseReport


    /**
     * 42. Report subject single
     */
    @FormUrlEncoded
    @POST("site/report_subjects_single")
    suspend fun reportSubjectsSingle(
        @Field("board") board: String,
        @Field("qbs_sub_id") boaqbs_sub_idrd: String,
    ): ReportSubjectSingle


    /**
     * 43. Over All report Subject based
     */
    @FormUrlEncoded
    @POST("site/overall_report")
    suspend fun overAllReportSubjectBased(
        @Field("board") board: String,
    ): com.students.qb365.ui.report.model.SubjectBasedTestReport


    /**
     * 44. Subjects for Chapter based report
     */
    @FormUrlEncoded
    @POST("site/creport_subjects")
    suspend fun subjectChapterReport(
        @Field("board") board: String,
    ): SubjectChapterBased


    /**
     * 45. Chapter for Chapter Based Report
     */
    @FormUrlEncoded
    @POST("site/rchapters")
    suspend fun chapterReport(
        @Field("board") board: String,
        @Field("qbs_sub_id") qbs_sub_id: String,
    ): ChapterReport


    /**
     * 46. Chapter Based Report
     */
    @FormUrlEncoded
    @POST("site/chapter_report")
    suspend fun chapterBasedReport(
        @Field("board") board: String,
        @Field("qbs_chapter_id") qbs_sub_id: String,
    ): ChapterBasedReport


    /**
     * 47. Order details
     */
    @POST("site/order_detail")
    suspend fun orderDetails(
    ): OrderDetail


    /**
     * 48. Create Customers (Stripe)
     */
    @POST("customers")
    fun createCustomers(@Header("Authorization") auth: String): Call<Customers>


    /**
     * 49. ephemeral_keys (Stripe)
     */
    @FormUrlEncoded
    @POST("ephemeral_keys")
    fun ephemeralKeys(
        @Header("Authorization") auth: String,
        @Header("Stripe-Version") stripeVersion: String,
        @Field("customer") customer: String
    ): Call<EphericalKey>


    /**
     * 50. payment Intent (Stripe)
     */
    @FormUrlEncoded
    @POST("payment_intents")
    fun paymentIntent(
        @Header("Authorization") auth: String,
        @Field("customer") customer: String,
        @Field("amount") amount: String,
        @Field("currency") currency: String,
        @Field("automatic_payment_methods[enabled]") enabled: String,
    ): Call<PaymentIntent>


    /**
     * 52. payment success
     */
    @POST("site/stripe_success")
    suspend fun paymentSuccess(
        @Header("Authorization") auth: String
    ): Payment

}











