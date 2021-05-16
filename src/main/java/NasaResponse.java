import com.fasterxml.jackson.annotation.JsonProperty;

public class NasaResponse {
    private String copyright;
    private String date;
    private String explanation;
    private String hdurl;
    private String mediaType;
    private String serviceVersion;
    private String title;
    private String url;

    public String getUrl() {
        return url;
    }

    public NasaResponse(
            @JsonProperty("copyright") String copyright,
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String hdurl,
            @JsonProperty("media_type") String media_type,
            @JsonProperty("service_version") String service_version,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url
    ) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.mediaType = media_type;
        this.serviceVersion = service_version;
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format("RESPONSE IS:\n" +
                "  copyright: %s\n" +
                "  date: %s\n" +
                "  explanation: %s\n" +
                "  hdurl: %s\n" +
                "  media_type: %s\n" +
                "  service_version: %s\n" +
                "  title: %s\n" +
                "  url: %s", copyright, date, explanation, hdurl, mediaType, serviceVersion, title, url);
    }
}

/*
{
RESPONSE IS:
  copyright: null
  date: 2021-05-16
  explanation: The clouds may look like an oyster, and the stars like pearls, but look beyond. Near the outskirts of the Small Magellanic Cloud, a satellite galaxy some 200 thousand light-years distant, lies 5 million year young star cluster NGC 602. Surrounded by natal gas and dust, NGC 602 is featured in this stunning Hubble image of the region. Fantastic ridges and swept back shapes strongly suggest that energetic radiation and shock waves from NGC 602's massive young stars have eroded the dusty material and triggered a progression of star formation moving away from the cluster's center. At the estimated distance of the Small Magellanic Cloud, the featured picture spans about 200 light-years, but a tantalizing assortment of background galaxies are also visible in this sharp multi-colored view. The background galaxies are hundreds of millions of light-years or more beyond NGC 602.
  hdurl: https://apod.nasa.gov/apod/image/2105/Ngc602_Hubble_3749.jpg
  media_type: image
  service_version: v1
  title: NGC 602 and Beyond
  url: https://apod.nasa.gov/apod/image/2105/Ngc602_Hubble_960.jpg
}
 */