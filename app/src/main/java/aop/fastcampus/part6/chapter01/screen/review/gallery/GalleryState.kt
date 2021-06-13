package aop.fastcampus.part6.chapter01.screen.review.gallery

sealed class GalleryState {

    object Uninitialized: GalleryState()

    object Loading: GalleryState()

    data class Success(
        val photoList: List<GalleryPhoto>
    ): GalleryState()

    data class Confirm(
        val photoList: List<GalleryPhoto>
    ): GalleryState()

}
