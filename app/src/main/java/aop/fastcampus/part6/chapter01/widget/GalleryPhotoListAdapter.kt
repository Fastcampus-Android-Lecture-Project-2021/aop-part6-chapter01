package aop.fastcampus.part6.chapter01.widget

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import aop.fastcampus.part6.chapter01.R
import aop.fastcampus.part6.chapter01.databinding.ViewholderGalleryPhotoItemBinding
import aop.fastcampus.part6.chapter01.extensions.load
import aop.fastcampus.part6.chapter01.screen.review.gallery.GalleryPhoto
import com.bumptech.glide.load.resource.bitmap.CenterCrop

class GalleryPhotoListAdapter(
    private val checkPhotoListener: (GalleryPhoto) -> Unit
) : RecyclerView.Adapter<GalleryPhotoListAdapter.PhotoItemViewHolder>() {

    private var galleryPhotoList: List<GalleryPhoto> = listOf()

    inner class PhotoItemViewHolder(
        private val binding: ViewholderGalleryPhotoItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: GalleryPhoto) = with(binding) {
            photoImageView.load(data.uri.toString(), scaleType = CenterCrop())
            checkButton.setImageDrawable(
                ContextCompat.getDrawable(
                    binding.root.context,
                    if (data.isSelected)
                        R.drawable.ic_check_enabled
                    else
                        R.drawable.ic_check_disabled
                )
            )
        }

        fun bindViews(data: GalleryPhoto) = with(binding) {
            root.setOnClickListener {
                checkPhotoListener(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {
        val view = ViewholderGalleryPhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        holder.bindData(galleryPhotoList[position])
        holder.bindViews(galleryPhotoList[position])
    }

    override fun getItemCount(): Int = galleryPhotoList.size

    fun setPhotoList(galleryPhotoList: List<GalleryPhoto>) {
        this.galleryPhotoList = galleryPhotoList
        notifyDataSetChanged()
    }
}
