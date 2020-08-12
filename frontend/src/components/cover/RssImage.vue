<template>
  <div class="col-6 mt-5 d-flex justify-content-center"></div>
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
