package aop.fastcampus.part6.chapter01.screen.review.camera.preview

import android.app.Activity
import android.content.Intent
import android.media.MediaScannerConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import aop.fastcampus.part6.chapter01.R
import aop.fastcampus.part6.chapter01.databinding.ActivityImagePreviewListBinding
import aop.fastcampus.part6.chapter01.util.path.PathUtil
import aop.fastcampus.part6.chapter01.widget.adapter.ImageViewPagerAdapter
import java.io.File
import java.io.FileNotFoundException

class ImagePreviewListActivity : AppCompatActivity() {

    companion object {
        private const val URI_LIST_KEY = "uriList"

        fun newIntent(activity: Activity, uriList: List<Uri>) =
            Intent(activity, ImagePreviewListActivity::class.java).apply {
                putExtra(URI_LIST_KEY, ArrayList<Uri>().apply { uriList.forEach { add(it) } })
            }
    }

    private lateinit var binding: ActivityImagePreviewListBinding
    private val uriList by lazy<List<Uri>> { intent.getParcelableArrayListExtra(URI_LIST_KEY)!! }
    private lateinit var imageViewPagerAdapter: ImageViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagePreviewListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        setSupportActionBar(binding.toolbar)
        setupImageList(uriList)
    }

    private fun setupImageList(uriList: List<Uri>) = with(binding) {
        if (::imageViewPagerAdapter.isInitialized.not()) {
            imageViewPagerAdapter = ImageViewPagerAdapter(uriList)
        }
        imageViewPager.adapter = imageViewPagerAdapter
        indicator.setViewPager(imageViewPager)
        imageViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                toolbar.title = getString(R.string.images_page, position + 1, imageViewPagerAdapter.uriList.size)
            }
        })
        deleteButton.setOnClickListener {
            removeImage(uriList[imageViewPager.currentItem])
        }
        confirmButton.setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra(URI_LIST_KEY, ArrayList<Uri>().apply { imageViewPagerAdapter.uriList.forEach { add(it) } })
            })
            finish()
        }
    }

    private fun removeImage(uri: Uri) {
        val file = File(PathUtil.getPath(this, uri) ?: throw FileNotFoundException())
        file.delete()
        imageViewPagerAdapter.uriList.let {
            val imageList = it.toMutableList()
            imageList.remove(uri)
            imageViewPagerAdapter.uriList = imageList
            imageViewPagerAdapter.notifyDataSetChanged()
        }
        binding.indicator.setViewPager(binding.imageViewPager)
        MediaScannerConnection.scanFile(this, arrayOf(file.path), arrayOf("image/jpeg"), null)
        if (imageViewPagerAdapter.uriList.isEmpty()) {
            Toast.makeText(this, "삭제할 수 있는 이미지가 없습니다.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

}
