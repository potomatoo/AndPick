<template>
  <div class="feed-explain">
    <div class="helper-text">피드<i class="mdi mdi-book-open"></i></div>
    <video
      src="@/assets/feedEx.mp4"
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
  name: "FeedExplain"
};
</script>

<style>
.helper-text {
  font-family: "Do Hyeon", sans-serif;
}
.feed-explain {
  margin-top: 35px;
  margin-left: 30px;
  background-color: white;
}
</style>
