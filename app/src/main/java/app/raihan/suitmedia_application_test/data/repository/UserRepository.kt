//package app.raihan.suitmedia_application_test.data.repository
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.liveData
//import app.raihan.suitmedia_application_test.data.service.ResponseModel
//import app.raihan.suitmedia_application_test.data.service.ApiService
//import kotlinx.coroutines.Dispatchers
//
//
//class UserRepository private constructor(
//    private val apiService: ApiService
//){
//
//    fun getListUser(page: String): LiveData<Result<ResponseModel>> =
//    liveData(Dispatchers.IO){
//        emit(Result.)
//        try {
//            val response = apiService.getListUser(page)
//            emit(Result.success(response))
//        }catch (e:Exception) {
//            emit(Result.Error(e.message.toString()))
//        }
//    }
//    companion object {
//        private const val TAG = "UserRepository"
//
//        @Volatile
//        private var instance: UserRepository? = null
//        fun getInstance(
//            apiService: ApiService
//        ): UserRepository =
//            instance ?: synchronized(this) {
//                instance ?: UserRepository(apiService)
//            }.also { instance = it }
//    }
//}
//
