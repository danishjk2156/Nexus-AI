# 🎬 NexusAI Studio

<div align="center">

### **100% Offline, Voice-First AI Video Editor for Mobile Creators**

[![Android](https://img.shields.io/badge/Platform-Android%2014%2B%20(API%2026%2B)-3DDC84?style=for-the-badge&logo=android&logoColor=white)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin%202.0-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose%20M3-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Zero Egress](https://img.shields.io/badge/Privacy-0%20Bytes%20Uploaded-00C853?style=for-the-badge&logo=privateinternetaccess&logoColor=white)](#-8-security--privacy-architecture)
[![Qualcomm NPU](https://img.shields.io/badge/Inference-Hexagon%20NPU%20%7C%20QNN-FF7043?style=for-the-badge&logo=qualcomm&logoColor=white)](https://developer.qualcomm.com)
[![GitHub](https://img.shields.io/badge/GitHub-Nexus--AI-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/danishjk2156/Nexus-AI)

<p align="center">
  <b>Speak a command in Hindi, English, or 99+ languages. The on-device AI generates a validated edit plan and executes multi-step video edits directly on your phone's NPU and GPU — zero cloud egress, zero latency wait, zero subscriptions.</b>
</p>

---

> *"NexusAI Studio is a 100% offline, voice-first video editor that turns one spoken command — in Hindi, English, or any of 99+ languages — into a complete multi-step edit, entirely on the phone's NPU and GPU. Zero bytes uploaded. Zero subscription. Zero cloud dependency. Just your phone, your voice, and your video."*

---

</div>

## ⚡ Performance At A Glance

| Metric | Target / Benchmark | Status |
| :--- | :--- | :---: |
| **Cloud Data Egress** | **0 Bytes Uploaded** (Complete Privacy) | ✅ Verified |
| **Command-to-Plan Latency** | **≤ 5 Seconds** on-device inference | ⚡ Sub-5s |
| **Multilingual Voice Support** | **99+ Languages** (Whisper Tiny/Base) | 🌐 Active |
| **Model Memory Footprint** | **< 1.5 GB RAM** (4-bit Quantized Gemma 2B) | 📱 4GB Ready |
| **Airplane Mode Operation** | **100% Functional** without Internet | ✈️ 100% Offline |
| **Hardware Acceleration** | **Hexagon NPU + Adreno GPU + MediaCodec** | 🚀 Triple-Engine |

---

## 📖 Table of Contents

- [🎯 1. Problem Statement & Market Reality](#-1-problem-statement--market-reality)
- [🏗️ 2. Core Solution Architecture](#️-2-core-solution-architecture)
  - [2.1 Principle: Three Small Specialists Beat One Giant Cloud Dependency](#21-principle-three-small-specialists-beat-one-giant-cloud-dependency)
  - [2.2 Four-Layer Hardware Pipeline](#22-four-layer-hardware-pipeline)
  - [2.3 Plan-to-Edit Guardrails & JSON Schema](#23-plan-to-edit-guardrails--json-schema)
- [📱 3. User Experience & App Workflow](#-3-user-experience--app-workflow)
- [🛠️ 4. Technology Stack & Hardware Mapping](#️-4-technology-stack--hardware-mapping)
- [📦 5. Repository Structure](#-5-repository-structure)
- [⚙️ 6. Running on Small Devices (4GB RAM Tier)](#️-6-running-on-small-devices-4gb-ram-tier)
- [🛡️ 7. Reliability & Thermal Management](#️-7-reliability--thermal-management)
- [🔒 8. Security & Privacy Architecture](#-8-security--privacy-architecture)
- [🚀 9. Getting Started & Building](#-9-getting-started--building)
- [⏱️ 10. Hackathon Implementation Plan](#️-10-hackathon-implementation-plan)

---

## 🎯 1. Problem Statement & Market Reality

India and emerging global markets represent **2–2.5 million monetised creators**, over **95% of whom produce content exclusively on mobile phones**. Current cloud-based video editing solutions fail mobile creators due to three fundamental bottlenecks:

```
[ Traditional Cloud AI Video Editor ]
Mobile Footage (375MB) ──(5 min upload on flaky 4G)──► Cloud Servers ──(Inference queue)──► Download
   ❌ Bandwidth cost       ❌ 78% creators worry about AI training       ❌ AI only starts after upload

[ NexusAI Studio (On-Device) ]
Mobile Footage (375MB) ──► Hexagon NPU + Adreno GPU ──► Instant Edit Preview (0 Bytes Egress)
   ✅ Instant Start        ✅ 100% Private (Footage never leaves device)  ✅ Zero Cloud Cost
```

### The Cloud Path vs. NexusAI Studio

| Dimension | Cloud AI Editors | NexusAI Studio |
| :--- | :--- | :--- |
| **Upload Overhead** | ~375 MB per 1-minute 4K clip at 10 Mbps | **0 bytes uploaded** — all local processing |
| **Turnaround Latency** | ~5–8 minutes upload + queue wait time | **Instant** — AI starts the second you shoot |
| **Privacy & IP Ownership** | Raw footage transmitted to 3rd-party servers | **Private by design** — raw media never leaves phone |
| **Network Dependency** | Completely dead in remote locations or airplane mode | **100% functional offline** anywhere, anytime |
| **Inference Cost** | Ongoing subscription ($15–$30/month) | **Zero server inference cost**; runs on device silicon |

---

## 🏗️ 2. Core Solution Architecture

### 2.1 Principle: Three Small Specialists Beat One Giant Cloud Dependency

NexusAI Studio separates **Intent** from **Execution**. Large language models are not suited to process raw video frames on mobile chipsets; instead, lightweight models specialize on dedicated silicon:

```mermaid
flowchart TD
    subgraph INPUT["Layer 1: Input"]
        V["Spoken Voice Command\n(Hindi / English / 99+ Langs)"]
        F["Raw Video Clips\n(Camera / Gallery)"]
    end

    subgraph INFERENCE["Layer 2: On-Device Specialists"]
        W["Whisper Tiny/Base\n(Hexagon NPU)\nSpeech-to-Text"]
        G["Gemma 2B (4-bit QNN)\n(Hexagon NPU)\nIntent Parser"]
        MP["MediaPipe / TFLite\n(Adreno GPU)\nVision & Highlight Detection"]
    end

    subgraph GUARDRAILS["Layer 3: Guardrail Engine"]
        J["Validated JSON Edit Plan\n(Regex + Schema Check)"]
        TR["Tool Router Allowlist\n(Highlights, Trim, Filter, Subtitles, Music)"]
    end

    subgraph EXECUTION["Layer 4: Hardware-Accelerated Output"]
        M3["Media3 Transformer\n(MediaCodec + OpenGL ES)"]
        O["Exported 4K/1080p Video\n(Zero Egress - Saved to Storage)"]
    end

    V --> W
    W -->|Normalized Text| G
    G -->|Raw Output| J
    J --> TR
    F --> MP
    MP -->|Timestamped Scene Scores| TR
    TR --> M3
    M3 --> O
```

1. **Whisper (Tiny/Base, Q4/Q5)**: Transcribes 16 kHz mono PCM speech into text across 99+ languages.
2. **Gemma 2B (4-bit)**: Acts strictly as an **intent parser** emitting structured JSON. *The model never sees video frames.*
3. **MediaPipe / TFLite**: Processes video frames on the GPU for facial expressions, motion delta, and visual highlights.
4. **Media3 Transformer**: Performs hardware-accelerated decode, trimming, shader filtering, and subtitle composition via `MediaCodec`.

---

### 2.2 Four-Layer Hardware Pipeline

Every task is routed to the exact processor engineered for it:

```
┌────────────────────────────────────────────────────────────────────────┐
│                          Compose UI Layer                              │
│       Reactive ViewModel • StateFlow • Real-Time Pipeline State        │
└───────────────────┬────────────────────────────────┬───────────────────┘
                    │                                │
     ┌──────────────▼─────────────┐   ┌──────────────▼─────────────┐
     │    Hexagon NPU Engine      │   │     Adreno GPU Engine      │
     │  • Whisper Speech-to-Text  │   │  • MediaPipe Vision Model  │
     │  • Gemma 2B Intent Parser  │   │  • Custom OpenGL ES Shaders│
     │  • 4-bit Weights via QNN   │   │  • Real-Time Video Preview │
     └──────────────┬─────────────┘   └──────────────┬─────────────┘
                    │                                │
                    └───────────────┬────────────────┘
                                    │
     ┌──────────────────────────────▼────────────────────────────┐
     │                   CPU & MediaCodec Engine                 │
     │   • JSON Schema Validation & Safety Tool Router           │
     │   • Hardware Video Decode / Encode (Media3 Transformer)   │
     │   • Canvas Subtitle Compositing & Checkpoint State        │
     └───────────────────────────────────────────────────────────┘
```

---

### 2.3 Plan-to-Edit Guardrails & JSON Schema

To prevent LLM hallucination and ensure deterministic video manipulation, Gemma 2B outputs a strict, schema-validated JSON payload.

#### Sample Validated Output:
```json
{
  "version": "1.0",
  "command": "Cut the first two seconds, add vivid filter, and generate Hindi subtitles",
  "actions": [
    {
      "type": "find_highlights",
      "count": 3,
      "metrics": ["motion", "face_expression"]
    },
    {
      "type": "trim",
      "start_offset_ms": 2000,
      "end_offset_ms": null
    },
    {
      "type": "apply_filter",
      "filter": "vivid",
      "intensity": 0.85
    },
    {
      "type": "add_subtitles",
      "language": "hi-IN",
      "style": "bold_yellow_stroke"
    }
  ]
}
```

#### Guardrail Protections:
- **Strict Allowlist**: Only 5 verified actions can execute (`find_highlights`, `trim`, `apply_filter`, `add_subtitles`, `add_music`).
- **Regex Fence Parsing**: The parser isolates ````json ... ```` blocks, rejecting malformed text.
- **Rule-Based Fallback**: If LLM output fails schema validation, the system gracefully falls back to deterministic regex and keyword parsing.

---

## 📱 3. User Experience & App Workflow

The application implements an 8-screen modular workflow built with Jetpack Compose:

```
[ 1. Setup ] ──────► [ 2. Home ] ◄──────────────────────┐
   Model check          Voice mic tap                   │
   NPU audit            Quick prompt chips              │
   Language packs       Recent projects                 │
                            │                           │
                            ▼                           │
                     [ 3. AI Plan ]                     │
                        Parsed intent cards             │
                        JSON action inspect             │
                            │                           │
                            ▼                           │
                    [ 4. Processing ]                   │
                        NPU / GPU live status           │
                        Step-by-step progress           │
                            │                           │
                            ▼                           │
                     [ 5. Editor ] ◄────────────────────┤
                        Interactive timeline            │
                        Multi-track preview             │
                        "Ask AI to Tweak" voice         │
                            │                           │
                            ▼                           │
                     [ 6. Export ] ─────────────────────┘
                        Format selection (4K/1080p)
                        0 Bytes Uploaded verification
                        Save / Share

[ Additional Tabs via Navigation ]:
├── [ 7. AI Skills ] (LoRA Pack Store: Cricket Highlights, Travel Vlog, Reel Master)
└── [ 8. Settings ] (Device Tiering, Thermal Throttling, Play Integrity, Zero Telemetry)
```

---

## 🛠️ 4. Technology Stack & Hardware Mapping

```
Technology Stack Overview
├── Language:           Kotlin 2.0.0
├── UI Toolkit:         Jetpack Compose (BOM 2024.12.01) + Material 3
├── Architecture:       MVVM + Kotlin StateFlow + Navigation Compose
├── Video Pipeline:     AndroidX Media3 Transformer + MediaCodec + OpenGL ES
├── Speech Engine:      Whisper.cpp / ONNX Runtime Mobile (Tiny/Base quantized)
├── Intent Engine:      Gemma 2B (4-bit QNN / GenieX runtime)
├── Vision Engine:      Google MediaPipe / LiteRT (TFLite GPU Delegate)
├── Verification:       Android Play Integrity API + Hardware TEE KeyStore
└── Target API:         Android SDK 35 (Min SDK 26 - Android 8.0+)
```

### Right Chip, Right Job

| Task | Chip Target | Runtime / Library | Throughput / Latency Budget |
| :--- | :--- | :--- | :--- |
| **Speech-to-Text** | Hexagon NPU | Whisper.cpp (Q4) | ~1.2s for 10s audio clip |
| **Intent Generation** | Hexagon NPU | Gemma 2B (4-bit QNN) | ~2.5s for JSON plan generation |
| **Highlight Analysis** | Adreno GPU | MediaPipe / LiteRT | ~35 FPS frame analysis |
| **Shader & Preview** | Adreno GPU | OpenGL ES 3.2 | 60 FPS real-time render |
| **Video Transcoding** | MediaCodec | AndroidX Media3 | Hardware H.264/HEVC encoder |
| **Plan Validation** | CPU (Kryo) | Kotlin Regex / Kotlinx.serialization | < 15ms safety check |

---

## 📦 5. Repository Structure

```
d:/iqgrand/
├── app/
│   ├── build.gradle.kts                # App-level dependencies & Compose configuration
│   └── src/main/
│       ├── AndroidManifest.xml         # Permissions (Microphone, Storage) & App Manifest
│       ├── java/com/nexusai/studio/
│       │   ├── MainActivity.kt         # Single-activity Compose container
│       │   ├── navigation/
│       │   │   └── NavGraph.kt         # Navigation Host & 8-screen route definitions
│       │   ├── ui/
│       │   │   ├── components/
│       │   │   │   └── Components.kt   # Reusable UI widgets (Lagoon theme, Mic button, Chips)
│       │   │   ├── screens/
│       │   │   │   ├── SetupScreen.kt      # On-device model audit & initial setup
│       │   │   │   ├── HomeScreen.kt       # Voice input, prompt chips, recent projects
│       │   │   │   ├── AIPlanScreen.kt     # Validated plan inspection & approval
│       │   │   │   ├── ProcessingScreen.kt # Hardware-accelerated pipeline monitor
│       │   │   │   ├── EditorScreen.kt     # Timeline scrubber, clip editing, preview
│       │   │   │   ├── ExportScreen.kt     # Zero-egress export & format picker
│       │   │   │   ├── AISkillsScreen.kt   # LoRA skill packs (Cricket, Reels, Travel)
│       │   │   │   └── SettingsScreen.kt   # Dynamic tiering, thermal rules, security
│       │   │   └── theme/
│       │   │       ├── Color.kt        # Deep Lagoon, Lime CTA, Sky accent palette
│       │   │       ├── Theme.kt        # Material 3 dark color scheme mapping
│       │   │       └── Type.kt         # Typography definitions
│       └── res/
│           └── values/
│               └── strings.xml         # String resources
├── build.gradle.kts                    # Root build configuration
├── settings.gradle.kts                 # Module definitions & plugin repositories
└── README.md                           # Documentation & system blueprint
```

---

## ⚙️ 6. Running on Small Devices (4GB RAM Tier)

To ensure smooth performance across both flagship phones and budget 4GB RAM devices, NexusAI Studio implements four key optimizations:

```
[ Flagship Tier (8-16GB RAM) ]
├── Gemma 2B (4-bit) + Whisper Base (Q5) + MediaPipe GPU
└── Full resolution real-time shader preview

[ Mid-Tier (6GB RAM) ]
├── Gemma 2B (4-bit) + Whisper Tiny (Q4) + MediaPipe GPU
└── Half-resolution live preview with background high-res encode

[ Budget Tier (4GB RAM) ]
├── Sequential Model Swapping (Whisper -> Gemma -> Unload)
├── LiteRT 3-bit Packed KV Cache (< 1.5GB total peak memory)
└── Rule-Based Intent Parser Fallback if NPU allocation exceeds 80%
```

### Key Memory Techniques:
1. **Sequential Model Loading**: Models are loaded and unloaded sequentially. Whisper frees memory immediately after speech-to-text before Gemma 2B takes over.
2. **3-bit Packed KV Cache**: LiteRT attention operators reduce KV cache memory by over 60%, maintaining prompt comprehension under strict memory bounds.
3. **Memory-Mapped Embeddings (mmap)**: LLM embedding tables are mapped directly from disk, preventing memory overhead until tokens are queried.

---

## 🛡️ 7. Reliability & Thermal Management

Mobile devices throttle clock speeds rapidly under continuous AI compute. Sustained inference can degrade throughput by up to 50% within minutes if unmanaged.

### 7.1 Burst-and-Yield Pattern
Inference tasks run in rapid sub-second bursts on the NPU and immediately yield compute cycles to allow silicon junctions to dissipate heat.

```
Compute Load
 ▲
 │   [Burst: 2.1s]               [Burst: 1.8s]
 │   Whisper Inference           Gemma Plan Gen
 │   ┌──────────────┐            ┌──────────────┐
 │   │              │   Yield    │              │   Yield
 ────┴──────────────┴────────────┴──────────────┴────────────► Time
     ◄── Cool down ──►           ◄── Cool down ──►
```

### 7.2 The Dynamic Fallback Ladder

```mermaid
graph TD
    A[Spoken Voice Command] --> B{NPU Available?}
    B -->|Yes| C[Hexagon NPU Whisper Tiny]
    B -->|No / High Temp| D[Quantized CPU Fallback]
    
    C --> E{LLM Responding in ≤5s?}
    D --> E
    
    E -->|Yes| F[Gemma 2B Validated JSON Plan]
    E -->|Timeout / Thermal Limit| G[Regex Rule-Based Intent Parser]
    
    F --> H{Plan Passes Schema & Allowlist?}
    H -->|Yes| I[Execute Hardware-Accelerated Edit]
    H -->|No| G
    G --> I
```

---

## 🔒 8. Security & Privacy Architecture

### 8.1 100% Privacy by Architecture
- **Zero Egress**: All media, transcripts, and LLM prompts stay strictly within device memory.
- **Offline Assurance**: The entire editor runs seamlessly with Airplane Mode enabled.
- **Zero Third-Party Telemetry**: No tracking SDKs, analytics pings, or user identifiers are included.

### 8.2 Model Integrity & Tamper Protection
- **Cryptographic Checksums**: Pre-packaged and downloaded models are validated using SHA-256 signatures prior to loading.
- **Play Integrity API**: Validates that the application binary executes on a genuine, certified Android hardware environment.
- **Hardware-Backed Keystores**: Sensitive cryptographic salts and model verification tokens are sealed inside the device's Trusted Execution Environment (TEE).

---

## 🚀 9. Getting Started & Building

### Prerequisites
- **Android Studio**: Ladybug (2024.2+) or newer
- **JDK**: Java 17 or higher
- **Android SDK**: API Level 35 (Android 15)
- **Target Device / Emulator**: Android 8.0+ (API 26+). Physical devices with Qualcomm Snapdragon 8 Gen 1/2/3 or Dimensity chipsets recommended for hardware NPU acceleration.

### Build and Run Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/danishjk2156/Nexus-AI.git
   cd Nexus-AI
   ```

2. **Verify project configuration**:
   Ensure `local.properties` specifies your Android SDK directory:
   ```properties
   sdk.dir=C:\\Users\\<YourUsername>\\AppData\\Local\\Android\\Sdk
   ```

3. **Compile the debug APK**:
   ```bash
   # Windows PowerShell
   .\gradlew assembleDebug

   # macOS / Linux
   ./gradlew assembleDebug
   ```

4. **Install to connected device**:
   ```bash
   adb install -r app/build/outputs/apk/debug/app-debug.apk
   ```

---

## ⏱️ 10. Hackathon Implementation Plan

| Hours | AI & On-Device Track | Android & Media Track | Product & UX Track |
| :---: | :--- | :--- | :--- |
| **00–04h** | Select & quantize Gemma 2B & Whisper Tiny | Project setup, Gradle configuration, Compose shell | User journey flow & test footage clips |
| **04–10h** | Model adapter integration & NPU smoke tests | Media3 pipeline, video decoding & rendering | Hindi strings & subtitle styling specifications |
| **10–18h** | Live Whisper, Gemma & MediaPipe pipelines | Timeline operations on real 4K/1080p clips | Wire full UI workflow to live models |
| **18–24h** | JSON tool router & schema guardrail engine | Auto-editor integration & export screen | 60-second demo script & slides preparation |
| **24–28h** | Latency benchmarking & fallback recovery tests | Thermal throttle monitoring & resume checkpoints | Airplane mode end-to-end rehearsal |
| **28–30h** | Model weight pruning & inference tuning | Final production APK release build | Final pitch & demo video showcase |

---

<div align="center">

### 🏆 NexusAI Studio — The Future of Private, On-Device Creation

**Designed with ❤️ for India and the World's Next Generation of Creators.**

</div>
