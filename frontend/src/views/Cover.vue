<template>
  <div>
    <v-parallax id="view" dark src="@/assets/cover.jpg" height="750">
      <div class="container">
        <!--Grid row-->
        <div class="row mt-5">
          <!--Grid column-->
          <div
            class="col-md-6 mb-5 mt-md-0 mt-5 text-white text-center text-md-left"
          >
            <h1
              class="h1-responsive font-weight-bold wow fadeInLeft"
              data-wow-delay="0.3s"
            >
              Sign up right now!
            </h1>
            <hr class="hr-light wow fadeInLeft" data-wow-delay="0.3s" />
            <h6 class="mb-3 wow fadeInLeft" data-wow-delay="0.3s">
              Lorem ipsum dolor sit amet, consectetur adipisicing elit. Rem
              repellendus quasi fuga nesciunt dolorum nulla magnam veniam
              sapiente, fugiat! Commodi sequi non animi ea dolor molestiae,
              quisquam iste, maiores. Nulla.
            </h6>
            <a
              class="btn btn-outline-white wow fadeInLeft"
              data-wow-delay="0.3s"
              >Learn more</a
            >
          </div>
        </div>
      </div>
    </v-parallax>

    <div class="container mt-5">
      <div class="row py-5">
        <ServiceDescription />
        <RssImage />
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
.v-parallax__image-container {
  transform: none !important;
  width: 100% !important;
}
</style>
