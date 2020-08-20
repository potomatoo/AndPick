<template>
  <div class="board-explain">
    <div class="helper-text">보드<i class="mdi mdi-book-open"></i></div>
    <video
      src="@/assets/boardEx.mp4"
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
  name: "BoardExplain"
};
</script>

<style>
.helper-text {
  font-family: "Do Hyeon", sans-serif;
}
.board-explain {
  margin-top: 35px;
  margin-left: 30px;
}
</style>
