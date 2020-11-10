
-- Spark

  # What are the different teams that use our dataset
    and what dimension & metrics is it helpful for them?
    + Conversion analysis
      - A/B Testing (Engagement ; exp1 vs exp2)
                    (Bookng; exp1 vs exp2 ; booking numbers )
                    (experiment_id, also based on visitor_id)
        . brand (vrbo, expedia)
        . site (vrbo ; us, france)

    + Marketing team
      -  Right now I only explain about the attribution and its usefulness for
         marketing team
         . channel
            . seo (Search results)
            . sem (Google Add)
            . email
            . direct
            . push notification
            . parter
         . entry_page_type -> homepage, property detail page
         . booking
         . pageviews
         . inquiry
         . dated_search
         . dateless_dated_search
         . trip_board
         . checkout

    + Partner




  # What are the different dataset (Aggregation datasets)?
    + visit_daily_fact
      - Has visit level aggregation
      - Contains attributino info
      - What are the attribution info
        . utm_medium
        . utm_term
        . ???
      - What are the possible 5/10 useful fields?
    + visitor_daily_fact
    + trip_board
      . created, upated, deleted
    + visitor_agg_keychain_id
      . Lookup with keychain
      . on different devices
      . login - public uuid
      . mobile - native_app_visitor_id

    + visitor_daily_fact or
    + visitor_agg_visit_id or
    + visitor_agg_keychain_id or


-- Kafka

  # Total amount of events / second? or /day?
    500 million events


  # On what key is userEvent & userSession is Partitioned?
    userEvent - visitor_id          - visitor_id
    userSession - server address    - visitor_id

    partition-event-edap - session_id (Redis)

    (I suppose both are co-partitioned)


  # Is that a KStream to KStream join (or) KStream to KTable join?
    + If we are using KStream to KStream join, are we using Sliding Window for the join?

  # What are all the many dimension & metric we get from Kafka topic?
    (The interviewer has asked what are all the different type of events
     I would create for clickstream)

  # What are the stuffs we are extracting from userAgent?

  # What are the fields we extract using MaxMind API?
    (The interviewer has asked how to get Geo information; I told through IP Address
     using Maxmind)

  # Page Type (Entry page, checkout, search page, checkout)
  # Interactions (Photo, clicks)
  # Marketing attributes
    . utm_medium (email, cpc (Google Ads), organic, social, referral)
    . utm_source (google, microsoft, facebook)
    . utm_campaign (email campaign, weekender, anyting that company creates)

  # MaxMind
    Region
    Country
    City
    State

-- How to filter Bot

  - Internal IP Address
    . Filter out internal IP address
  - No of pageviews
    . If no of page views is high for a visit
  - Page Duration
    . If page duration is small compared to the average page duration
  - Bounce rate
    . If Bounce rate is high
  - Page Loading time
    . If page loading time is high, we might be getting swamped by Bot


  /*def res(input: List[String]): List[String] = {
    import scala.collection.mutable.ListBuffer
    val buffer = ListBuffer[String]() ++ input
    val size = buffer.size
    println("List size -> " + size)
    for(i <- 0 to size - 1) {
      val str = buffer.remove(0)
      buffer.toList.filter{ word =>
        check(str, word) match {
          case ("", "") => true
          case _ => false
        }
      } match {
        case result if result.size >= 1 =>
          println(s"$str is an anagram")
        case _ =>
          println(s"$srt is not an anagram")
      }
      buffer.append(str)
    }
  }*/
