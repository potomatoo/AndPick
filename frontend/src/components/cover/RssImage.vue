<template>
  <div class="col-lg-6 mt-5 d-flex justify-content-center">
    <!-- <video src="@/assets/Subscribe.mp4" width="490" autoplay muted loop>
      video 태그
    </video> -->
  </div>
</template>

<script>
export default {};
</script>

<style>
.box {
  -webkit-transform: translate(0, 0);
  -webkit-transition: -webkit-transform 500ms;

  transform: translate(0, 0);
  transition: transform 500ms;
}

.box.move {
  -webkit-transform: translate(100px, 100px);
  transform: translate(100px, 100px);
}
</style>
