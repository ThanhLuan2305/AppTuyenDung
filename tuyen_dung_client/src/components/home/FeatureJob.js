import moment from "moment";

const FeatureJob = (props) => {
  
  const handleSplitTime = (time) => {
    return moment(new Date(+time)).fromNow();
  };
  return (
    <>
      <div class="single-job-items mb-30">
        <div class="job-items">
          <div class="company-img">
            <a href="job_details.html">
              <img
                src={props.data.companyThumbnailValue}
                alt=""
                style={{ width: "85px", height: "85px" }}
              />
            </a>
          </div>
          <div class="job-tittle">
            <a href="job_details.html">
              <h4>{props.data.nameValue}</h4>
            </a>
            <ul>
              <li>{props.data.categoryJobLevelCodeValue}</li>
              <li>
                <i class="fas fa-map-marker-alt"></i>
                {props.data.addressCodeValue}
              </li>
              <li><i class="fas fa-money-bill-wave"></i>{props.data.salaryCodeValue}</li>
            </ul>
          </div>
        </div>
        <div class="items-link items-link2 f-right">
          <a href="job_details.html">
            {props.data.categoryWorktypeCodeValue}
          </a>
          <span style={{ position: "absolute", right: "70px" }}>
            {handleSplitTime(props.data.timePostValue)}
          </span>
        </div>
      </div>
    </>
  );
};
export default FeatureJob;
