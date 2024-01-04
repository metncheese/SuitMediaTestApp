package app.raihan.suitmedia_application_test.ui.third

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.raihan.suitmedia_application_test.adapter.RVAdapter
import app.raihan.suitmedia_application_test.data.response.DataItem
import app.raihan.suitmedia_application_test.data.response.ListUserResponse
import app.raihan.suitmedia_application_test.data.service.ApiConfig
import app.raihan.suitmedia_application_test.databinding.ActivityThirdScreenBinding
import app.raihan.suitmedia_application_test.ui.second.SecondScreenActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdScreenActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var adapterUser: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = screen3

        binding.refresh.setOnRefreshListener(this)

        adapterUser = RVAdapter()
        binding.rvResultUser.layoutManager = LinearLayoutManager(this)
        binding.rvResultUser.adapter = adapterUser
        binding.rvResultUser.setHasFixedSize(true)
        adapterUser.setClickCallback(object : RVAdapter.OnItemClickCallback {
            override fun onItemClicked(user: DataItem) {
                Intent(this@ThirdScreenActivity, SecondScreenActivity::class.java). also {
                    it.putExtra(SecondScreenActivity.EXTRA_NAME, user.firstName)
                    startActivity(it)
                    page = 1
                    finish()
                }
            }
        })

        getUsers(false)
    }

    private fun getUsers(isRefresh: Boolean){
        isLoading = true
        if (!isRefresh) {
            binding.progressbar.visibility = View.VISIBLE}

        Handler().postDelayed({
            val params = HashMap<String, String>()
            params["page"] = page.toString()
            ApiConfig.getApiService().getUsers(params).enqueue(object : Callback<ListUserResponse> {
                override fun onResponse(call: Call<ListUserResponse>, response: Response<ListUserResponse>) {
                    if (response.isSuccessful) {
                        totalPage = response.body()?.totalPages!!
                        val listUsers = response.body()?.data
                        Log.d(screen3, "onResponse: $listUsers")
                        if(listUsers!!.isNotEmpty()){
                            adapterUser.setList(listUsers as ArrayList<DataItem>)
                        }
                        binding.progressbar.visibility = View.GONE
                        isLoading = false
                        binding.refresh.isRefreshing =false
                    }
                }

                override fun onFailure(call: Call<ListUserResponse>, t: Throwable) {
                    Log.d("onFailure", t.message.toString())
                    Toast.makeText(this@ThirdScreenActivity, "Connection Failed...", Toast.LENGTH_SHORT).show()
                    binding.progressbar.visibility = View.GONE
                    isLoading = false
                    binding.refresh.isRefreshing =false
                }

            }) }, 3000)
    }

    override fun onRefresh() {
        adapterUser.clearUsers()
        page = 2
        getUsers(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private var screen3 ="Third Screen"
        private var isLoading = false
        private var page: Int = 1
        private var totalPage: Int = 1
    }
}

private fun ListUserResponse.enqueue(callback: Callback<ListUserResponse>) {
    TODO("Not yet implemented")
}
