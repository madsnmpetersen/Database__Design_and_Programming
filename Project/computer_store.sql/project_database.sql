--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.1
-- Dumped by pg_dump version 9.4.1
-- Started on 2015-03-24 19:30:56

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2069 (class 1262 OID 16393)
-- Name: computer store; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "computer store" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Danish_Denmark.1252' LC_CTYPE = 'Danish_Denmark.1252';


ALTER DATABASE "computer store" OWNER TO postgres;

\connect "computer store"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2070 (class 1262 OID 16393)
-- Dependencies: 2069
-- Name: computer store; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE "computer store" IS 'Database for use with project';


--
-- TOC entry 180 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2073 (class 0 OID 0)
-- Dependencies: 180
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 202 (class 1255 OID 16649)
-- Name: cpucompatible(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION cpucompatible() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

BEGIN
IF (SELECT getcpusocket(NEW.CPU)) <> (SELECT getmainsocket(NEW.mainboard)) THEN
	RAISE EXCEPTION 'CPU and mainboard CPU sockets must match';
ELSE
	return NEW;
	
END IF;
END;
$$;


ALTER FUNCTION public.cpucompatible() OWNER TO postgres;

--
-- TOC entry 198 (class 1255 OID 16650)
-- Name: formcompatible(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION formcompatible() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

BEGIN
IF (SELECT gettowerformfactor(NEW.tower)) <> (SELECT getmainformfactor(NEW.mainboard)) THEN
	RAISE EXCEPTION 'mainboard and tower form factor must match';
ELSE
	return NEW;
	
END IF;
END;

$$;


ALTER FUNCTION public.formcompatible() OWNER TO postgres;

--
-- TOC entry 203 (class 1255 OID 16645)
-- Name: getcpusocket(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION getcpusocket(newcpu character varying) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
declare 
	mine varchar;
BEGIN
SELECT cpu_socket into mine FROM CPU WHERE CPU.name = newCpu;
return mine;
END;
$$;


ALTER FUNCTION public.getcpusocket(newcpu character varying) OWNER TO postgres;

--
-- TOC entry 197 (class 1255 OID 16648)
-- Name: getmainformfactor(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION getmainformfactor(newmain character varying) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
declare 
	mine varchar;
BEGIN
SELECT formfactor into mine FROM mainboard WHERE mainboard.name = newMain;
return mine;
END;
 $$;


ALTER FUNCTION public.getmainformfactor(newmain character varying) OWNER TO postgres;

--
-- TOC entry 194 (class 1255 OID 16638)
-- Name: getmainramtype(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION getmainramtype(newmain character varying) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
declare 
	mine varchar;
BEGIN
SELECT ram_type into mine FROM mainboard WHERE mainboard.name = newMain;
return mine;
END;
$$;


ALTER FUNCTION public.getmainramtype(newmain character varying) OWNER TO postgres;

--
-- TOC entry 195 (class 1255 OID 16646)
-- Name: getmainsocket(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION getmainsocket(newmain character varying) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
declare 
	mine varchar;
BEGIN
SELECT cpu_socket into mine FROM mainboard WHERE mainboard.name = newMain;
return mine;
END;
 $$;


ALTER FUNCTION public.getmainsocket(newmain character varying) OWNER TO postgres;

--
-- TOC entry 200 (class 1255 OID 16656)
-- Name: getonboardgraphics(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION getonboardgraphics(newmain character varying) RETURNS boolean
    LANGUAGE plpgsql
    AS $$
declare 
	mine boolean;
BEGIN
SELECT onboard_graphics into mine FROM mainboard WHERE mainboard.name = newMain;
return mine;
END;
 $$;


ALTER FUNCTION public.getonboardgraphics(newmain character varying) OWNER TO postgres;

--
-- TOC entry 193 (class 1255 OID 16637)
-- Name: getramtype(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION getramtype(newram character varying) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
declare 
	mine varchar;
BEGIN
SELECT ram_type into mine FROM ram WHERE ram.name = newRam;
return mine;
END;
$$;


ALTER FUNCTION public.getramtype(newram character varying) OWNER TO postgres;

--
-- TOC entry 196 (class 1255 OID 16647)
-- Name: gettowerformfactor(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION gettowerformfactor(newtower character varying) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
declare 
	mine varchar;
BEGIN
SELECT formfactor into mine FROM tower WHERE tower.name = newTower;
return mine;
END;
 $$;


ALTER FUNCTION public.gettowerformfactor(newtower character varying) OWNER TO postgres;

--
-- TOC entry 201 (class 1255 OID 16654)
-- Name: graphicscompatible(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION graphicscompatible() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

BEGIN
IF ((SELECT getonboardgraphics(NEW.mainboard)) = false) AND (NEW.graphics IS NULL) THEN
	RAISE EXCEPTION 'If there is no onboard graphics card one must be specified';
ELSE
	return NEW;
	
END IF;
END;

$$;


ALTER FUNCTION public.graphicscompatible() OWNER TO postgres;

--
-- TOC entry 199 (class 1255 OID 16643)
-- Name: ramcompatible(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION ramcompatible() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

BEGIN
IF (SELECT getramtype(NEW.ram)) <> (SELECT getmainramtype(NEW.mainboard)) THEN
	RAISE EXCEPTION 'mainboard and ram types must match';
ELSE
	return NEW;
	
END IF;
END;

$$;


ALTER FUNCTION public.ramcompatible() OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 172 (class 1259 OID 16399)
-- Name: component; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE component (
    name character varying(30) NOT NULL,
    price integer,
    kind character varying(10),
    qty integer
);


ALTER TABLE component OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 16429)
-- Name: cpu; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cpu (
    name character varying(30) NOT NULL,
    cpu_socket character varying(20),
    cpu_speed integer
);


ALTER TABLE cpu OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 16444)
-- Name: graphics; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE graphics (
    name character varying(30) NOT NULL,
    type character varying(10)
);


ALTER TABLE graphics OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16419)
-- Name: mainboard; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE mainboard (
    name character varying(30) NOT NULL,
    formfactor character varying(20),
    cpu_socket character varying(20),
    ram_type character varying(20),
    onboard_graphics boolean
);


ALTER TABLE mainboard OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16434)
-- Name: ram; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE ram (
    name character varying(30) NOT NULL,
    ram_type character varying(10),
    speed integer
);


ALTER TABLE ram OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 16449)
-- Name: stock_rules; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE stock_rules (
    name character varying(30) NOT NULL,
    min_qty integer,
    pref_qty integer
);


ALTER TABLE stock_rules OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 16439)
-- Name: system; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE system (
    name character varying(30) NOT NULL,
    ram character varying(30) NOT NULL,
    mainboard character varying(30) NOT NULL,
    cpu character varying(30) NOT NULL,
    tower character varying(30) NOT NULL,
    graphics character varying(30)
);


ALTER TABLE system OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 16414)
-- Name: tower; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tower (
    name character varying(30) NOT NULL,
    formfactor character varying(10)
);


ALTER TABLE tower OWNER TO postgres;

--
-- TOC entry 2057 (class 0 OID 16399)
-- Dependencies: 172
-- Data for Name: component; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY component (name, price, kind, qty) FROM stdin;
i5-3.5GHz	500	CPU	5
MSI Z97	130	mainboard	3
Kingston HyperX 2GB	50	RAM	9
Crucial Ballistix 4GB	98	RAM	6
i7-4GHz	1000	CPU	3
Corsair Carbide Series SPEC-01	30	tower	9
Fractal Design Define R5	60	tower	4
ASUS Z97	130	mainboard	10
amd_cpu_1	130	CPU	10
amd_cpu_2	200	CPU	10
intel_cpu_2	300	CPU	10
graphics_card_1	300	graphics	10
graphics_card_2	400	graphics	10
graphics_card_3	500	graphics	10
graphics_card_4	250	graphics	10
graphics_card_5	350	graphics	10
ram_1	250	RAM	10
ram_2	350	RAM	10
ram_3	450	RAM	10
tower_3	350	tower	10
tower_4	400	tower	10
mainboard_1	100	mainboard	10
mainboard_3	150	mainboard	10
mainboard_4	250	mainboard	10
ram_4	550	RAM	10
mainboard_2	200	mainboard	10
intel_cpu_1	187	CPU	10
tower_1	250	tower	10
tower_2	300	tower	10
\.


--
-- TOC entry 2060 (class 0 OID 16429)
-- Dependencies: 175
-- Data for Name: cpu; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cpu (name, cpu_socket, cpu_speed) FROM stdin;
i7-4GHz	LGA1150	4000
i5-3.5GHz	LGA1150	3500
amd_cpu_1	amd_socket	333
amd_cpu_2	amd_socket	666
intel_cpu_2	intel_socket	3000
intel_cpu_1	intel_socket	2000
\.


--
-- TOC entry 2063 (class 0 OID 16444)
-- Dependencies: 178
-- Data for Name: graphics; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY graphics (name, type) FROM stdin;
graphics_card_1	nvidia
graphics_card_2	nvidia
graphics_card_3	nvidia
graphics_card_4	ati
graphics_card_5	ati
\.


--
-- TOC entry 2059 (class 0 OID 16419)
-- Dependencies: 174
-- Data for Name: mainboard; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY mainboard (name, formfactor, cpu_socket, ram_type, onboard_graphics) FROM stdin;
ASUS Z97	ATX	LGA1150	DDR3	t
MSI Z97	ATX	LGA1150	DDR3	t
mainboard_1	ATX	intel_socket	DDR3	f
mainboard_2	ATX	intel_socket	DDR4	f
mainboard_3	ATX	amd_socket	DDR3	f
mainboard_4	ATX	amd_socket	DDR4	f
\.


--
-- TOC entry 2061 (class 0 OID 16434)
-- Dependencies: 176
-- Data for Name: ram; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY ram (name, ram_type, speed) FROM stdin;
Kingston HyperX 2GB	DDR3	2400
Crucial Ballistix 4GB	DDR3	1600
ram_1	DDR3	1666
ram_2	DDR3	2000
ram_3	DDR4	2500
ram_4	DDR4	3000
\.


--
-- TOC entry 2064 (class 0 OID 16449)
-- Dependencies: 179
-- Data for Name: stock_rules; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY stock_rules (name, min_qty, pref_qty) FROM stdin;
mainboard_1	1	10
i5-3.5GHz	1	3
ASUS Z97	5	10
Fractal Design Define R5	2	4
amd_cpu_1	1	10
amd_cpu_2	1	10
intel_cpu_1	1	10
intel_cpu_2	1	10
graphics_card_1	1	10
graphics_card_2	1	10
graphics_card_3	1	10
graphics_card_4	1	10
graphics_card_5	1	10
ram_1	1	10
ram_2	1	10
mainboard_2	1	10
ram_3	1	10
ram_4	1	10
tower_1	1	10
tower_2	1	10
tower_3	1	10
tower_4	1	10
mainboard_3	1	10
mainboard_4	1	10
\.


--
-- TOC entry 2062 (class 0 OID 16439)
-- Dependencies: 177
-- Data for Name: system; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY system (name, ram, mainboard, cpu, tower, graphics) FROM stdin;
system_1	Crucial Ballistix 4GB	ASUS Z97	i7-4GHz	Fractal Design Define R5	\N
system_2	ram_1	mainboard_1	intel_cpu_1	tower_1	graphics_card_1
system_3	ram_2	mainboard_2	intel_cpu_2	tower_2	graphics_card_2
system_6	ram_3	mainboard_2	intel_cpu_1	tower_1	graphics_card_1
system_4	ram_4	mainboard_2	intel_cpu_1	tower_1	graphics_card_1
system_5	ram_2	mainboard_1	intel_cpu_2	tower_1	graphics_card_1
system_7	ram_1	mainboard_3	amd_cpu_2	tower_3	graphics_card_1
\.


--
-- TOC entry 2058 (class 0 OID 16414)
-- Dependencies: 173
-- Data for Name: tower; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY tower (name, formfactor) FROM stdin;
Fractal Design Define R5	ATX
Corsair Carbide Series SPEC-01	ATX
tower_1	ATX
tower_2	ATX
tower_3	ATX
tower_4	ATX
\.


--
-- TOC entry 1919 (class 2606 OID 16455)
-- Name: component_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY component
    ADD CONSTRAINT component_pkey PRIMARY KEY (name);


--
-- TOC entry 1925 (class 2606 OID 16465)
-- Name: cpu_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cpu
    ADD CONSTRAINT cpu_pkey PRIMARY KEY (name);


--
-- TOC entry 1931 (class 2606 OID 16475)
-- Name: graphics_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY graphics
    ADD CONSTRAINT graphics_pkey PRIMARY KEY (name);


--
-- TOC entry 1923 (class 2606 OID 16486)
-- Name: mainboard_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY mainboard
    ADD CONSTRAINT mainboard_pkey PRIMARY KEY (name);


--
-- TOC entry 1927 (class 2606 OID 16504)
-- Name: ram_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY ram
    ADD CONSTRAINT ram_pkey PRIMARY KEY (name);


--
-- TOC entry 1933 (class 2606 OID 16514)
-- Name: stock_rules_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY stock_rules
    ADD CONSTRAINT stock_rules_pkey PRIMARY KEY (name);


--
-- TOC entry 1929 (class 2606 OID 16520)
-- Name: system_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY system
    ADD CONSTRAINT system_pkey PRIMARY KEY (name);


--
-- TOC entry 1921 (class 2606 OID 16546)
-- Name: tower_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tower
    ADD CONSTRAINT tower_pkey PRIMARY KEY (name);


--
-- TOC entry 1945 (class 2620 OID 16651)
-- Name: iscpusocketcompatible; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER iscpusocketcompatible BEFORE INSERT OR UPDATE ON system FOR EACH ROW EXECUTE PROCEDURE cpucompatible();


--
-- TOC entry 1946 (class 2620 OID 16652)
-- Name: isformfactorcompatible; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER isformfactorcompatible BEFORE INSERT OR UPDATE ON system FOR EACH ROW EXECUTE PROCEDURE formcompatible();


--
-- TOC entry 1947 (class 2620 OID 16655)
-- Name: isgraphicscompatible; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER isgraphicscompatible BEFORE INSERT OR UPDATE ON system FOR EACH ROW EXECUTE PROCEDURE graphicscompatible();


--
-- TOC entry 1944 (class 2620 OID 16644)
-- Name: isramcompatible; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER isramcompatible BEFORE INSERT OR UPDATE ON system FOR EACH ROW EXECUTE PROCEDURE ramcompatible();


--
-- TOC entry 1939 (class 2606 OID 16621)
-- Name: cpuIsInCPU; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY system
    ADD CONSTRAINT "cpuIsInCPU" FOREIGN KEY (cpu) REFERENCES cpu(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1941 (class 2606 OID 16631)
-- Name: graphicsIsInGraphics; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY system
    ADD CONSTRAINT "graphicsIsInGraphics" FOREIGN KEY (graphics) REFERENCES graphics(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1938 (class 2606 OID 16616)
-- Name: mainboardIsInMainboard; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY system
    ADD CONSTRAINT "mainboardIsInMainboard" FOREIGN KEY (mainboard) REFERENCES mainboard(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1935 (class 2606 OID 16556)
-- Name: nameIsInComponent; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cpu
    ADD CONSTRAINT "nameIsInComponent" FOREIGN KEY (name) REFERENCES component(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1942 (class 2606 OID 16561)
-- Name: nameIsInComponent; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY graphics
    ADD CONSTRAINT "nameIsInComponent" FOREIGN KEY (name) REFERENCES component(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1934 (class 2606 OID 16566)
-- Name: nameIsInComponent; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mainboard
    ADD CONSTRAINT "nameIsInComponent" FOREIGN KEY (name) REFERENCES component(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1936 (class 2606 OID 16571)
-- Name: nameIsInComponent; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY ram
    ADD CONSTRAINT "nameIsInComponent" FOREIGN KEY (name) REFERENCES component(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1943 (class 2606 OID 16581)
-- Name: nameIsInComponent; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY stock_rules
    ADD CONSTRAINT "nameIsInComponent" FOREIGN KEY (name) REFERENCES component(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1937 (class 2606 OID 16611)
-- Name: ramIsInRam; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY system
    ADD CONSTRAINT "ramIsInRam" FOREIGN KEY (ram) REFERENCES ram(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 1940 (class 2606 OID 16626)
-- Name: towerIsInTower; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY system
    ADD CONSTRAINT "towerIsInTower" FOREIGN KEY (tower) REFERENCES tower(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2072 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-03-24 19:30:56

--
-- PostgreSQL database dump complete
--

