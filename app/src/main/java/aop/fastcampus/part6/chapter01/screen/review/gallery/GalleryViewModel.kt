package aop.fastcampus.part6.chapter01.screen.review.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GalleryViewModel(
    private val galleryPhotoRepository: GalleryPhotoRepository
) : ViewModel() {

    private lateinit var photoList: MutableList<GalleryPhoto>

    val galleryStateLiveData = MutableLiveData<GalleryState>(GalleryState.Uninitialized)

    fun fetchData() = viewModelScope.launch {
        setState(
            GalleryState.Loading
        )
        photoList = galleryPhotoRepository.getAllPhotos()
        setState(
            GalleryState.Success(
                photoList = photoList
            )
        )
    }

    fun selectPhoto(galleryPhoto: GalleryPhoto) {
        val findGalleryPhoto = photoList.find { it.id == galleryPhoto.id }
        findGalleryPhoto?.let { photo ->
            photoList[photoList.indexOf(photo)] =
                photo.copy(
                    isSelected = photo.isSelected.not()
                )
            setState(
                GalleryState.Success(
                    photoList = photoList
                )
            )
        }
    }

    fun confirmCheckedPhotos() {
        setState(
            GalleryState.Loading
        )
        setState(
            GalleryState.Confirm(
                photoList = photoList.filter { it.isSelected }
            )
        )
    }

    private fun setState(state: GalleryState) {
        galleryStateLiveData.postValue(state)
    }

}
