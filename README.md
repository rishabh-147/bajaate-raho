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
<img src="https://img.shields.io/badge/Status-Active-yellow?style=for-the-badge"/>
</p>

</div>

<hr/>

🎧 What is Bajaate Raho?
Bajaate Raho is a lightweight self-hosted music streaming backend.<br/>
Unlike traditional applications that upload entire audio files, this project implements the same approach used by modern streaming platforms:

Stream only the required bytes when the browser requests them.

<hr/>

🖼️ Screenshots
<div align="center">
<img src="src/asset/screenshot1.png" alt="Bajaate Raho Screenshot" width="800"/>
<img src="src/asset/screenshot2.png" alt="Bajaate Raho Screenshot" width="800"/>
<img src="src/asset/screenshot3.png" alt="Bajaate Raho Screenshot" width="800"/>
</div>

<hr/>

🏗️ System Architecture
<pre>
USER
|
React Frontend
GitHub Pages
|
HTTPS
|
☁ Cloudflare Tunnel
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
Local Music Library
MP3 Collection
</pre>

<hr/>

☁ Cloudflare Tunnel Integration
<h3 align="center">Securely exposing a self-hosted backend without opening ports.</h3>

Traditional deployment:<br/>
<pre>
Internet → Public IP → Router Port Forwarding → Backend
</pre>

Bajaate Raho deployment:<br/>
<pre>
Backend → Spring Boot → Cloudflare Tunnel → Cloudflare Edge → Internet Users
</pre>

Benefits:<br/>
🔒 No public IP exposure<br/>
🔐 Automatic HTTPS<br/>
🚫 No router port forwarding<br/>
🌎 Accessible globally

<hr/>

🚀 Features
🎵 Streaming Engine → HTTP Range Requests, 206 Partial Content, Browser seeking<br/>
📻 Radio Engine → Random song selection, Metadata API, React player integration<br/>
📂 Catalog System → Recursive scanning, Song indexing, Metadata caching<br/>
🌐 Deployment → Cloudflare Tunnel, HTTPS communication, Self-hosted backend

<hr/>

🔄 Streaming Lifecycle
<pre>
Browser → GET /v1/radio
Spring Boot → Catalog → SongMetadata
Browser → GET /v1/songs/{id} with Range
Spring Boot → Filesystem → Audio Stream
Spring Boot → Browser → 206 Partial Content
</pre>

<hr/>

📡 API Reference
Random Radio<br/>
<pre>
GET /v1/radio
</pre>
Response:<br/>
<pre>
{
"id":15,
"title":"Song Name",
"fileName":"song.mp3"
}
</pre>

Stream Audio<br/>
<pre>
GET /v1/songs/{id}
Range: bytes=start-end
Response: 206 Partial Content
</pre>

<hr/>

📁 Backend Structure
<pre>
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
</pre>

<hr/>

🧠 Engineering Highlights
HTTP Range Streaming → Faster start, efficient bandwidth, seeking support<br/>
DecoratedInputStream → Responds only with requested byte ranges<br/>
Catalog Service → Separates song discovery from streaming pipeline

<hr/>

⚙️ Configuration
application.yml<br/>
<pre>
music:
folder: ${MUSIC_FOLDER:C:/Users/User/Music}
</pre>

Override:<br/>
Windows → set MUSIC_FOLDER=D:\Songs<br/>
Linux → export MUSIC_FOLDER=/home/user/Music

<hr/>

🛠️ Tech Stack
Technology	Usage
Java 21	Backend
Spring Boot	REST API
Spring MVC	Streaming
Maven	Build
React	Frontend
HTML5 Audio API	Playback
Cloudflare Tunnel	Secure Deployment


<hr/>

🗺️ Roadmap
✅ Completed → HTTP Streaming, Browser Seeking, Random Radio, Catalog System, React Integration, Cloudflare Deployment<br/>
🚧 Planned → MP3 Metadata Extraction, Album Artwork, Search, Playlists, Lyrics, Analytics, Authentication, Multi-user Support

<hr/>

<div align="center">

<h3>
Built with ☕ curiosity and a lot of debugging.
</h3>

</div>