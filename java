import React, { useRef } from 'react';
import { motion, useScroll, useTransform, useSpring } from 'framer-motion';
import CreativeCoreDisplay from './CreativeCoreDisplay';

const AssemblingLab = () => {
  const containerRef = useRef(null);
  
  // Track scroll progress of this specific section
  const { scrollYProgress } = useScroll({
    target: containerRef,
    offset: ["start end", "end start"]
  });

  // Smoothing the scroll input for a premium feel
  const smoothProgress = useSpring(scrollYProgress, {
    stiffness: 100,
    damping: 30,
    restDelta: 0.001
  });

  // Transformation mappings
  const scale = useTransform(smoothProgress, [0, 0.4], [0.8, 1]);
  const opacity = useTransform(smoothProgress, [0, 0.3], [0, 1]);
  const slideLeft = useTransform(smoothProgress, [0, 0.4], [-100, 0]);
  const slideRight = useTransform(smoothProgress, [0, 0.4], [100, 0]);
  const glassBlur = useTransform(smoothProgress, [0, 0.4], ["blur(20px)", "blur(0px)"]);

  return (
    <section ref={containerRef} className="py-40 bg-[#0A0A0A] overflow-hidden flex justify-center items-center">
      <motion.div 
        style={{ scale, opacity }}
        className="relative w-full max-w-5xl px-6"
      >
        
        {/* 1. THE GOLD AURA (Ignites on scroll) */}
        <motion.div 
          style={{ opacity: useTransform(smoothProgress, [0.3, 0.5], [0, 0.6]) }}
          className="absolute -inset-4 bg-yellow-600/20 rounded-[3rem] blur-[80px] z-0"
        />

        {/* 2. THE CHASSIS (Assembling from sides) */}
        <div className="relative z-10">
          
          {/* Left Bracket */}
          <motion.div 
            style={{ x: slideLeft }}
            className="absolute -left-2 top-0 bottom-0 w-[2px] bg-gradient-to-b from-transparent via-yellow-500 to-transparent shadow-[0_0_15px_#D4AF37]"
          />

          {/* Right Bracket */}
          <motion.div 
            style={{ x: slideRight }}
            className="absolute -right-2 top-0 bottom-0 w-[2px] bg-gradient-to-b from-transparent via-yellow-500 to-transparent shadow-[0_0_15px_#D4AF37]"
          />

          {/* MAIN CONTAINER */}
          <motion.div 
            style={{ backdropFilter: glassBlur }}
            className="relative overflow-hidden rounded-xl border border-white/10 bg-white/[0.03] backdrop-blur-3xl"
          >
            {/* Top HUD Overlay */}
            <div className="p-4 border-b border-white/5 flex justify-between items-center bg-black/40">
              <div className="flex gap-2">
                <div className="w-2 h-2 rounded-full bg-red-500/50" />
                <div className="w-2 h-2 rounded-full bg-yellow-500/50" />
                <div className="w-2 h-2 rounded-full bg-green-500/50" />
              </div>
              <span className="font-mono text-[9px] text-gray-500 tracking-[0.2em]">INITIATING_CORE_SEQUENCE...</span>
            </div>

            {/* THE CORE ENGINE */}
            <div className="h-[600px] w-full bg-black/20">
               <CreativeCoreDisplay />
            </div>

            {/* Bottom Data Bar */}
            <motion.div 
              initial={{ width: "0%" }}
              whileInView={{ width: "100%" }}
              transition={{ duration: 1.5, delay: 0.5 }}
              className="h-[2px] bg-yellow-600/50"
            />
            <div className="p-6 flex justify-between items-end">
               <div>
                  <h4 className="text-yellow-500 font-bold text-xs uppercase tracking-widest">AiQSol Core V.4</h4>
                  <p className="text-gray-500 text-[10px] mt-1 italic">Enterprise Media & AI Synthesis</p>
               </div>
               <div className="text-right">
                  <p className="text-white font-mono text-lg tracking-tighter">00:04:92:01</p>
                  <p className="text-[9px] text-gray-600 uppercase">System Uptime</p>
               </div>
            </div>
          </motion.div>
        </div>

      </motion.div>
    </section>
  );
};

export default AssemblingLab;
