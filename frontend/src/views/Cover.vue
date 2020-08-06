<template>
  <div
    class="view text-white"
    style="background-image: url('https://images.unsplash.com/photo-1556888335-95371827d5fb?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1789&q=80'); background-repeat: no-repeat; background-size: cover; background-position: center center;"
  >
    <div class="container">
      <div class="row py-5">
        <ServiceDescription />
        <!-- <RssImage /> -->
      </div>
      <hr />
      <ServiceStep />
      <hr />
      <UserInformation />
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from "vue-property-decorator";
import ServiceDescription from "@/components/cover/ServiceDescription.vue";
import RssImage from "@/components/cover/RssImage.vue";
import UserInformation from "@/components/cover/UserInformation.vue";
import ServiceStep from "@/components/cover/ServiceStep.vue";

@Component({
  components: {
    ServiceDescription,
    RssImage,
    UserInformation,
    ServiceStep
  }
})
export default class Cover extends Vue {}
</script>

<style>
.view {
  background-attachment: fixed;
  background-size: auto;
  height: 1300px;
}
</style>
