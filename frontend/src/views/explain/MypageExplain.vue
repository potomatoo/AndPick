<template>
  <div class="mypage-explain">
    <div class="helper-text">마이페이지<i class="mdi mdi-book-open"></i></div>
    <video
      src="@/assets/mypageEx.mp4"
      width="1000"
      controls
      autoplay
      muted
      loop
    ></video>
  </div>
</template>

<script>
export default {
  name: "MypageExplain"
};
</script>

<style>
.helper-text {
  font-family: "Do Hyeon", sans-serif;
}
.mypage-explain {
  margin-top: 35px;
  margin-left: 30px;
}
</style>
