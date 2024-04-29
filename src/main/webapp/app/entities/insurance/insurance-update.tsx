import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IInsurance } from 'app/shared/model/insurance.model';
import { getEntity, updateEntity, createEntity, reset } from './insurance.reducer';

export const InsuranceUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const insuranceEntity = useAppSelector(state => state.insurance.entity);
  const loading = useAppSelector(state => state.insurance.loading);
  const updating = useAppSelector(state => state.insurance.updating);
  const updateSuccess = useAppSelector(state => state.insurance.updateSuccess);

  const handleClose = () => {
    navigate('/insurance');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.insID !== undefined && typeof values.insID !== 'number') {
      values.insID = Number(values.insID);
    }

    const entity = {
      ...insuranceEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...insuranceEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="wayThereJhipsterApp.insurance.home.createOrEditLabel" data-cy="InsuranceCreateUpdateHeading">
            <Translate contentKey="wayThereJhipsterApp.insurance.home.createOrEditLabel">Create or edit a Insurance</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="insurance-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('wayThereJhipsterApp.insurance.insID')}
                id="insurance-insID"
                name="insID"
                data-cy="insID"
                type="text"
              />
              <ValidatedField
                label={translate('wayThereJhipsterApp.insurance.insDescription')}
                id="insurance-insDescription"
                name="insDescription"
                data-cy="insDescription"
                type="text"
              />
              <ValidatedField
                label={translate('wayThereJhipsterApp.insurance.startDate')}
                id="insurance-startDate"
                name="startDate"
                data-cy="startDate"
                type="date"
              />
              <ValidatedField
                label={translate('wayThereJhipsterApp.insurance.expirationDate')}
                id="insurance-expirationDate"
                name="expirationDate"
                data-cy="expirationDate"
                type="date"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/insurance" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default InsuranceUpdate;
