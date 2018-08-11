     clj-video notes

# Desired Properties

  - Commands can pipeline 
    * transducers ?
  - Agnostic to provider 
    * ffmpeg via java command-line wrapper
      [ffmpeg cli wrapper](https://github.com/bramp/ffmpeg-cli-wrapper)
    * Elastic transcoder/AWS Elemental MediaConvert
    * Produce and Onyx Workflow
  - Folding
    * When available a series of transformations can be folded into one 
      step to maximize efficiency
  - Fan in/Fan out
    * Simple way to manage common steps where multiple input blocks
      can be pipelined to a common output target and single inputs
      can output multiple destination containers.
      i.e. multibitrate 
  - Configuration-based style to describe backing implementations
    * Containers
    * Codecs
    * Presets (?)


# Sketch of Pipeline

  - Container
  - Configuration
  - Operation

  ?  Aren't all pipelines:

    Con(s) * OP(s) => Con(s)

    INC * OP  * OP -> MP4
                OP -> DASH1
                      DASH2
                      DASH3

          FANO  ENC

  Operation
    :inputs
      * Set of containers
    :outputs
      * Set of containers
    :configuration
      * Operation specific metadata

  Container
    :type 
    :metadata {:vcodec H.264 :acodec AAC}
    :location

  EX: Segment input, resize segments, re-package into new output container

  C (c1)                OP (Segment)                  OP (Resize)             OP (Package)
  :type MP4             :inputs [c1]                  :inputs [s1 s2 s3]      :inputs [r1 r2 r3]
  :location XXX         :outputs [s1 s2 s3]           :outputs [r1 r2 r3] .   :outputs [p1]
  :metadata (fprobe?)   :tansform {:segment-size ...} :transform {:dims ...}  :transform {:container-type MOV}

                                                      Should some ops be able
                                                      to handle 1-1 mapping
                                                      or should it be multiple
                                                      ops?

  ? Segmented to multiple output containers

  OP -> [OPs] -> [OPs]

    multiple ops act as a map operation?


  ? Is there ever a reasonable case where multiple ops need to be consolidated?

     - segment -> transcode into multiple formats -> take set of multiple formats and ??

  ? How about the case 
      segment -> multiple resizes -> multiple output containers


   => The transform of the OP supports a mode: i.e. fan-in, fan-out, map

   => Ops use this mode to determine chaining behaviour

      fan-out

        Applies transform to each input container (with multiple output containers per input) 

      map 

        1-1 transformation of inputs to outputs

      fan-in

        Uses entire sequence of input containers to produce fewer output containers


  # Editing?

    PiP/Overlay/Subtitle map

    Clip fan-in
    
    Insert

      This really would be OP level consolidation 

        segment   OP 
        segment   OP    into on OP

        * REAALLLY not a great thing to do. Better in a real workflow system

      


    

# Open questions

  - Is there a way to use transducers to make creating a sequence
    of transformation steps while keeping parallelization?

    * i.e. split stream -> each segment downscaled -> multiple output formats

  - How do you formalize the ability for a backing implementation 
    to (optionally) consolidate operations 

    * i.e.  ffmpeg can potentially optimize some transformation tasks
            when knowing up-front what the pipeline is. Ideally, don't 
            want to lose this capability but don't want to force it 
            on the interface. (low friction for use, otherwise stays out
            of your way....)

  - Can volitile/core async provide parallelization within a composed
    transducer to support multiple outputs/inputs for fan-in/fan-out?

  - Is there any value in using Onyx-style workflow approach to describe
    the topology of pipeline?  Is this way of expressing things at odds with
    having individual transformations be transducers?
