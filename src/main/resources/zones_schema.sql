--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE IF EXISTS zones;
--
-- Name: zones; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE zones WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';


ALTER DATABASE zones OWNER TO postgres;

\connect zones

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: coordinate; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE coordinate (
    id bigint NOT NULL,
    latitude double precision NOT NULL,
    longitude double precision NOT NULL,
    CONSTRAINT coordinate_latitude_check CHECK (((latitude <= (90)::double precision) AND (latitude >= ('-90'::integer)::double precision))),
    CONSTRAINT coordinate_longitude_check CHECK (((longitude <= (180)::double precision) AND (longitude >= ('-180'::integer)::double precision)))
);


ALTER TABLE coordinate OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- Name: triangular_zone; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE triangular_zone (
    id bigint NOT NULL,
    name character varying(255),
    coordinate_1_id bigint NOT NULL,
    coordinate_2_id bigint NOT NULL,
    coordinate_3_id bigint NOT NULL
);


ALTER TABLE triangular_zone OWNER TO postgres;


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 1, false);


--
-- Name: coordinate coordinate_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY coordinate
    ADD CONSTRAINT coordinate_pkey PRIMARY KEY (id);


--
-- Name: triangular_zone triangular_zone_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY triangular_zone
    ADD CONSTRAINT triangular_zone_pkey PRIMARY KEY (id);


--
-- Name: triangular_zone fk8reipqsofhjh7fddhm8cv7daq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY triangular_zone
    ADD CONSTRAINT fk8reipqsofhjh7fddhm8cv7daq FOREIGN KEY (coordinate_1_id) REFERENCES coordinate(id);


--
-- Name: triangular_zone fkcf24elhwfx6u0j7r8afcg9fgg; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY triangular_zone
    ADD CONSTRAINT fkcf24elhwfx6u0j7r8afcg9fgg FOREIGN KEY (coordinate_2_id) REFERENCES coordinate(id);


--
-- Name: triangular_zone fknhslc9882dtklodnqrwfle8i6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY triangular_zone
    ADD CONSTRAINT fknhslc9882dtklodnqrwfle8i6 FOREIGN KEY (coordinate_3_id) REFERENCES coordinate(id);


--
-- PostgreSQL database dump complete
--

