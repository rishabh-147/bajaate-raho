<div align="center">

<img src="src/asset/logo.svg" width="120" />

<h1>🎵 Bajaate Raho</h1>

<h3>
A self-hosted internet radio streaming platform built from scratch.
</h3>

<p>
Spring Boot • HTTP Range Streaming • Cloudflare Tunnel • React Audio Engine
</p>


<p>

<img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk"/>
<img src="https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=for-the-badge&logo=springboot"/>
<img src="https://img.shields.io/badge/HTTP-Range_Streaming-blue?style=for-the-badge"/>
<img src="https://img.shields.io/badge/Cloudflare-Tunnel-orange?style=for-the-badge&logo=cloudflare"/>

</p>

<p>
<img src="https://img.shields.io/badge/Status-Active-yellow?style=for-the-badge"/>
</p>


</div>


---

# 🎧 What is Bajaate Raho?


**Bajaate Raho** is a lightweight self-hosted music streaming backend.

Unlike traditional applications that upload entire audio files, this project implements the same approach used by modern streaming platforms:

> Stream only the required bytes when the browser requests them.


The backend handles:

<table>
<tr>
<td width="50%">

### 🎵 Audio Streaming

- HTTP Range Requests
- Partial Content (206)
- Browser seeking
- Efficient buffering

</td>

<td width="50%">

### 📂 Music Management

- Recursive scanning
- Metadata catalog
- Random radio selection
- Local storage support

</td>
</tr>
</table>


---

# 🏗️ System Architecture


<div align="center">

```
                         USER

                          |
                          |

                   React Frontend

                    GitHub Pages

                          |

                         HTTPS

                          |

                 ☁ Cloudflare Tunnel

                          |

                          |

                Spring Boot Backend


        +-----------------+----------------+

        |                                  |

 Catalog Service                  Streaming Service

        |                                  |

 Song Metadata                    HTTP Range Response

        |                                  |

        +-----------------+----------------+

                          |

                          |

                 Local Music Library

                    MP3 Collection

```

</div>


---

# ☁ Cloudflare Tunnel Integration


<div align="center">

<h3>
Securely exposing a self-hosted backend without opening ports.
</h3>

</div>


Traditional deployment:

```
Internet
   |
Public IP
   |
Router Port Forwarding
   |
Backend
```


Bajaate Raho:

```
Backend Machine

Spring Boot

    |

Cloudflare Tunnel

    |

Cloudflare Edge

    |

Internet Users
```


### Why Cloudflare Tunnel?

<table>

<tr>
<td>🔒</td>
<td>
No public IP exposure
</td>
</tr>

<tr>
<td>🔐</td>
<td>
Automatic HTTPS
</td>
</tr>

<tr>
<td>🚫</td>
<td>
No router port forwarding
</td>
</tr>

<tr>
<td>🌎</td>
<td>
Accessible globally
</td>
</tr>

</table>


---

# 🚀 Features


<table>

<tr>

<td>

## 🎵 Streaming Engine

✅ HTTP Range Requests  
✅ 206 Partial Content  
✅ Browser seeking  
✅ InputStream streaming  
✅ Low memory consumption


</td>


<td>

## 📻 Radio Engine

✅ Random song selection  
✅ Metadata API  
✅ React player integration  
✅ Automatic next song


</td>

</tr>


<tr>

<td>

## 📂 Catalog System

✅ Recursive scanning  
✅ Song indexing  
✅ Metadata caching


</td>


<td>

## 🌐 Deployment

✅ Cloudflare Tunnel  
✅ HTTPS communication  
✅ Self-hosted backend


</td>

</tr>


</table>


---

# 🔄 Streaming Lifecycle


```
sequenceDiagram

Browser->>Spring Boot:
GET /v1/radio

Spring Boot->>Catalog:
Select Random Song

Catalog-->>Spring Boot:
SongMetadata


Browser->>Spring Boot:
GET /v1/songs/{id}

Browser->>Spring Boot:
Range: bytes=100000-

Spring Boot->>Filesystem:
Open FileInputStream

Filesystem-->>Spring Boot:
Audio Stream

Spring Boot-->>Browser:
206 Partial Content

```


---

# 📡 API Reference


## 📻 Random Radio


```
GET /v1/radio
```


Returns:

```json
{
"id":15,
"title":"Song Name",
"fileName":"song.mp3"
}
```


---


## 🎵 Stream Audio


```
GET /v1/songs/{id}
```


Supports:

```
Range: bytes=start-end
```


Response:

```
206 Partial Content
```


---

# 📁 Backend Structure


```
src

├── catalog

│   ├── CatalogService

│   ├── CatalogActualService

│   └── SongMetadata


├── streaming

│   ├── StreamController

│   ├── StreamingService

│   ├── SongResource

│   └── DecoratedInputStream


├── config

│   └── CorsConfig


└── resources

    └── application.yml

```


---

# 🧠 Engineering Highlights


<details>

<summary><b>Why HTTP Range Streaming?</b></summary>


Browsers request audio in chunks:


```
Range: bytes=500000-
```


Benefits:

- Faster start time
- Efficient bandwidth
- Seeking support


</details>



<details>

<summary><b>Why DecoratedInputStream?</b></summary>


Normal FileInputStream:


```
Start Offset → EOF
```


Problem:

The browser requested only a limited range.


Solution:


```
Start Offset → Required Bytes Only
```


DecoratedInputStream controls the response length.


</details>



<details>

<summary><b>Why Catalog Service?</b></summary>


The catalog layer separates:


```
Finding songs

      from

Streaming bytes
```


This keeps the streaming pipeline lightweight.

</details>



---

# ⚙️ Configuration


`application.yml`

```yaml
music:
  folder: ${MUSIC_FOLDER:C:/Users/User/Music}
```


Override:


Windows

```cmd
set MUSIC_FOLDER=D:\Songs
```


Linux

```bash
export MUSIC_FOLDER=/home/user/Music
```


---

# 🛠️ Tech Stack


| Technology | Usage |
|-|-|
| Java 21 | Backend |
| Spring Boot | REST API |
| Spring MVC | Streaming |
| Maven | Build |
| React | Frontend |
| HTML5 Audio API | Playback |
| Cloudflare Tunnel | Secure Deployment |


---

# 🗺️ Roadmap


✅ Completed

- HTTP Streaming
- Browser Seeking
- Random Radio
- Catalog System
- React Integration
- Cloudflare Deployment


🚧 Planned

- MP3 Metadata Extraction
- Album Artwork
- Search
- Playlists
- Lyrics
- Analytics
- Authentication
- Multi-user Support


---

<div align="center">


<h3>
Built with ☕ curiosity and a lot of debugging.
</h3>


</div>