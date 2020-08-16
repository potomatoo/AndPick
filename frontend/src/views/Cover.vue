<template>
  <div>
    <div class="back row py-5">
      <ServiceDescription />
      <RssImage />
    </div>
    <ServiceStep />
    <UserInformation />
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
.back {
  /* background-image: url("https://images.unsplash.com/photo-1556888335-95371827d5fb?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1789&q=80"); */
  background-size: 100% 100%;
  background-color: #17212c;
  height: 500px;
  margin: 1px;
  border: 15px solid #00d59b !important;
}
</style>
