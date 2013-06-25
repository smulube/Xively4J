
Initialize API client

```java
XivelyClient client = new XivelyClient();
client.setApiKey("YOURAPIKEY");
```

Get feed object from the API

```java
Feed feed = client.feeds().getFeed("504");
```

Update feed

```java
List<Datastream> datastreams = new ArrayList<Datastream>();
Datastream datastream1 = new Datastream();
datastream1.setId("sensor1");
datastream1.setCurrentValue("37.2");
datastreams.add(datastream1);

Datastream datastream2 = new Datastream();
datastream2.setId("sensor2");
datastream2.setCurrentValue("827");
datastreams.add(datastream2);

feed.setDatastreams(datastreams);
client.feeds().updateFeed(feed);
```

```java
client.channels().createChannel(feed, channel);

```java
client.channels(feed).createChannel(channel);
client.channels(feed).deleteChannel("sensor1");
```
